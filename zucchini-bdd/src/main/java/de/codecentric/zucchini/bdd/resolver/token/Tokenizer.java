/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.codecentric.zucchini.bdd.resolver.token;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
    /**
     * The default variable prefix string is "${".
     */
    @SuppressWarnings("WeakerAccess")
    public static final String DEFAULT_PREFIX = "${";

    /**
     * The default variable suffix string is "}".
     */
    @SuppressWarnings("WeakerAccess")
    public static final String DEFAULT_SUFFIX = "}";

    /**
     * The default escape character is "\"".
     */
    @SuppressWarnings("WeakerAccess")
    public static final String DEFAULT_ESCAPE_CHARACTER = "\"";

    /**
     * This pattern is used to split a string into tokens while keeping the delimiters.
     */
    private static final String SPLIT_TOKEN_PATTERN_TEMPLATE = "((?<=%1$s)|(?=%1$s)|(?<=%2$s)|(?=%2$s))";

    /**
     * This pattern describes valid variable names.
     * <p/>
     * Examples for valid names:
     * - Abc
     * - abc
     * - a1b
     * <p/>
     * Examples for invalid names:
     * - 123
     * - 1ab
     * - $
     */
    private static final String VARIABLE_PATTERN = "^[a-zA-Z][a-zA-Z0-9\\.\\-_]*$";

    /**
     * This pattern is used to find all literals (i.e. alphanumeric strings) while keeping escaped strings (e.g. "a b")
     * together.
     */
    private static final String ESCAPED_LITERAL_PATTERN_TEMPLATE = "([^%1$s][a-zA-Z0-9]*|%1$s.+?%1$s)\\s*";

    /**
     * This pattern is used to remove all leading and trailing non-alphanumeric characters.
     */
    private static final String TRIM_SPECIAL_CHARACTERS_PATTERN = "^[^a-zA-Z0-9]*(.*?)[^a-zA-Z0-9]*$";

    private final String prefix;
    private final String suffix;
    private final String escapeCharacter;

    private String variable;
    private TokenizerState state;
    private TokenList mergedTokens;
    private int index;
    private String[] rawTokens;
    private String cleanedToken;

    /** 
     * Initializes a tokenizer.
     */
    public Tokenizer() {
        this(DEFAULT_PREFIX, DEFAULT_SUFFIX, DEFAULT_ESCAPE_CHARACTER);
    }

    /**
     * Initializes a tokenizer with custom prefix, suffix, and escape character.
     *
     * @param prefix          The variable prefix.
     * @param suffix          The variable suffix.
     * @param escapeCharacter The escape character.
     */
    public Tokenizer(String prefix, String suffix, String escapeCharacter) {
        assertArgumentsAreValid(prefix, suffix, escapeCharacter);
        this.prefix = prefix;
        this.suffix = suffix;
        this.escapeCharacter = escapeCharacter;
    }

    private void assertArgumentsAreValid(final String prefix, final String suffix, final String escapeCharacter) {
        if (prefix == null || prefix.isEmpty()) {
            throwArgumentError("The variable prefix must not be null or empty.");
        } else if (suffix == null || suffix.isEmpty()) {
            throwArgumentError("The variable suffix must not be null or empty.");
        } else if (escapeCharacter == null || escapeCharacter.isEmpty()) {
            throwArgumentError("The escape character must not be null or empty.");
        } else if (prefix.equals(suffix)) {
            throwArgumentError("Variable prefix and suffix must not be equal.");
        } else if (prefix.equals(escapeCharacter)) {
            throwArgumentError("Variable prefix and escape character must not be equal.");
        } else if (suffix.equals(escapeCharacter)) {
            throwArgumentError("Variable suffix and escape character must not be equal.");
        }
    }

    public TokenList tokenize(String statementName) {
        assertStatementIsValid(statementName);
        String splitCriterion =
                String.format(SPLIT_TOKEN_PATTERN_TEMPLATE, Pattern.quote(prefix), Pattern.quote(suffix));
        rawTokens = statementName.split(splitCriterion);
        findTokens();
        return mergedTokens;
    }

    private void assertStatementIsValid(final String statementName) {
        if (statementName == null || statementName.isEmpty()) {
            throwArgumentError("The statement name must not be null or empty.");
        }
    }

    /**
     * Finds tokens within the split statement name.
     */
    private void findTokens() {
        mergedTokens = new TokenList();
        variable = null;
        state = TokenizerState.START;
        index = -1;

        while (index < rawTokens.length) {
            switch (state) {
            case START:
                next(TokenizerState.OUTSIDE_VARIABLE_CONTEXT);
                break;
            case OUTSIDE_VARIABLE_CONTEXT:
                handleTokenOutsideOfVariableContext();
                break;
            case INSIDE_VARIABLE_CONTEXT:
                handleTokenInsideOfVariableContext();
                break;
            default:
                break;
            }
        }
    }

    private void handleTokenInsideOfVariableContext() {
        if (tokenIsPrefix()) {
            throwError("Syntax error: Nested variables are not supported.");
        }

        if (tokenIsSuffix()) {
            parseBufferedVariable();
            next(TokenizerState.OUTSIDE_VARIABLE_CONTEXT);
        } else {
            // We are in a variable context and the current token is the
            // variable name. The variable is stored and not directly added as a token,
            // because we want to make sure that the next token is "}".
            bufferVariable();
            next();
        }
    }

    private void handleTokenOutsideOfVariableContext() {
        if (tokenIsSuffix()) {
            throwError(String.format("Syntax error: Found %s before %s.", suffix, prefix));
        }

        if (tokenIsPrefix()) {
            next(TokenizerState.INSIDE_VARIABLE_CONTEXT);
        } else if (tokenHasContent()) {
            parseLiteral(cleanedToken);
            next(TokenizerState.OUTSIDE_VARIABLE_CONTEXT);
        } else {
            next();
        }
    }

    private void next() {
        next(state);
    }

    private void next(final TokenizerState nextState) {
        state = nextState;

        if (isLastToken() && state == TokenizerState.INSIDE_VARIABLE_CONTEXT) {
            throwError(String.format("Syntax error: Missing %s.", suffix));
        }

        if (++index < rawTokens.length) {
            cleanedToken = rawTokens[index].trim();
        }
    }

    private boolean isLastToken() {
        return index >= rawTokens.length - 1;
    }

    /**
     * This method splits a string into sub tokens so that only alphanumeric and escaped strings become literals.
     * <p/>
     * Examples:
     * - "a-b-c" => "a", "b", "c"
     * - "a-" => "a"
     * - "\"a b c\"" => "a b c"
     *
     * @param token A raw token that may contain multiple literals.
     */
    private void parseLiteral(String token) {
        Matcher m = Pattern.compile(String.format(ESCAPED_LITERAL_PATTERN_TEMPLATE, escapeCharacter)).matcher(token);
        while (m.find()) {
            cleanedToken = trimSpecialCharacters(m.group(1));
            if (tokenHasContent()) {
                mergedTokens.add(new LiteralToken(cleanedToken));
            }
        }
    }

    /**
     * Remove leading and trailing non-alphanumeric characters.
     *
     * @param string The string.
     * @return The given string without leading and trailing non-alphanumerical characters.
     */
    private String trimSpecialCharacters(String string) {
        return string.replaceAll(TRIM_SPECIAL_CHARACTERS_PATTERN, "$1");
    }

    private void bufferVariable() {
        variable = cleanedToken;
    }

    private void parseBufferedVariable() {
        assertVariableNameIsValid();
        mergedTokens.add(new VariableToken(variable));
        variable = null;
    }

    private void assertVariableNameIsValid() {
        if (variable == null) {
            // Situation: "${}"
            throwError(String.format("Syntax error: Found %s after %s but didn't find a" +
                    " variable name.", suffix, prefix));
        } else if (variableNameContainsForbiddenCharacters()) {
            throwError(String.format("Syntax error: Found variable with name \"%s\" which does not match %s.",
                    variable, VARIABLE_PATTERN));
        } else if (variableNameAlreadyExists()) {
            throwError(String.format("Variable duplication error: Found variable with name \"%s\" twice.",
                    variable));
        }
    }

    private boolean variableNameContainsForbiddenCharacters() {
        return !variable.matches(VARIABLE_PATTERN);
    }

    private boolean variableNameAlreadyExists() {
        return mergedTokens.containsVariableWithName(variable);
    }

    private boolean tokenHasContent() {
        return cleanedToken != null && !cleanedToken.isEmpty();
    }

    private boolean tokenIsSuffix() {
        return suffix.equals(cleanedToken);
    }

    private boolean tokenIsPrefix() {
        return prefix.equals(cleanedToken);
    }

    private void throwArgumentError(final String message) {
        state = TokenizerState.ERROR;
        throw new IllegalArgumentException(message);
    }

    private void throwError(final String message) {
        state = TokenizerState.ERROR;
        throw new TokenizerException(message);
    }
}

enum TokenizerState {
    START,
    INSIDE_VARIABLE_CONTEXT,
    OUTSIDE_VARIABLE_CONTEXT,
    ERROR
}

