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

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokenizer {
    /**
     * The default variable prefix string is "${".
     */
    public static final String DEFAULT_PREFIX = "${";

    /**
     * The default variable suffix string is "}".
     */
    public static final String DEFAULT_SUFFIX = "}";

    /**
     * The default escape character is "\"".
     */
    public static final String DEFAULT_ESCAPE_CHARACTER = "\"";

    /**
     * This pattern is used to split a string into tokens while keeping the delimiters.
     */
    private static final String SPLIT_TOKEN_PATTERN_TEMPLATE = "((?<=%1$s)|(?=%1$s)|(?<=%2$s)|(?=%2$s))";

    /**
     * This pattern describes valid variable names.
     *
     * Examples for valid names:
     * - Abc
     * - abc
     * - a1b
     *
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
        this.prefix = prefix;
        this.suffix = suffix;
        this.escapeCharacter = escapeCharacter;
    }

    public TokenList tokenize(String statementName) {
        String splitCriterion = String.format(SPLIT_TOKEN_PATTERN_TEMPLATE, Pattern.quote(prefix), Pattern.quote(suffix));
        String[] splittedStatementName = statementName.split(splitCriterion);
        return findTokens(splittedStatementName);
    }

    /**
     * Finds tokens within the splitted statement name.
     *
     * @param splittedStatementName
     * @return
     */
    private TokenList findTokens(String[] splittedStatementName) {
        TokenList mergedTokens = new TokenList();
        boolean inVariableContext = false;
        String variable = null;
        for (String token : splittedStatementName) {
            String cleanedToken = token.trim();

            // Is the token the beginning of a variable?
            if (prefix.equals(cleanedToken)) {
                // Situation: "${${"
                if (inVariableContext) {
                    throw new RuntimeException("Syntax error: Nested variables are not supported.");
                }

                // The next token should be the variable name. Remember this!
                inVariableContext = true;
            }

            // Are we currently parsing a variable?
            if (inVariableContext) {
                if (suffix.equals(cleanedToken)) {
                    // We are no longer parsing a variable.
                    inVariableContext = false;

                    // Situation: "${}"
                    if (variable == null) {
                        throw new RuntimeException("Syntax error: Found } after ${ but didn't find a variable name.");
                    }

                    // Check whether the variable contains forbidden characters.
                    if (!variable.matches(VARIABLE_PATTERN)) {
                        throw new RuntimeException(String.format("Syntax error: Found variable with name \"%s\" which does not match %s.", variable, VARIABLE_PATTERN));
                    }

                    mergedTokens.add(new VariableToken(variable));
                    variable = null;
                } else {
                    // The previous token was "${", i.e. we are in a variable context and the current token is the
                    // variable name. The variable is stored and not directly added as a token because we want to make
                    // sure that the next token is "}".
                    variable = cleanedToken;
                }
                // Are we parsing the end of a variable?                
            } else if (suffix.equals(cleanedToken)) {
                // Situation: "}"
                throw new RuntimeException("Syntax error: Found } before ${.");
                // Are we parsing a literal token?
            } else if (!cleanedToken.isEmpty()) {
                // A token may consist of multiple literals, e.g. "a-b" is considered as the two distinct literals "a"
                // and "b". Additionally, literals can be escaped with a quote sign (").
                mergedTokens.addAll(parseLiteral(cleanedToken));
            }
        }

        // Situation: "${var"
        if (inVariableContext && variable != null) {
            throw new RuntimeException("Syntax error: Missing }.");
        }

        return mergedTokens;
    }

    /**
     * This method splits a string into sub tokens so that only alphanumeric and escaped strings become literals.
     *
     * Examples:
     * - "a-b-c" => "a", "b", "c"
     * - "a-" => "a"
     * - "\"a b c\"" => "a b c"
     *
     * @param token
     * @return
     */
    private List<Token> parseLiteral(String token) {
        List<Token> tokens = new ArrayList<Token>();
        Matcher m = Pattern.compile(String.format(ESCAPED_LITERAL_PATTERN_TEMPLATE, escapeCharacter)).matcher(token);
        while (m.find()) {
            String cleanedToken = trimSpecialCharacters(m.group(1));
            if (!cleanedToken.isEmpty()) {
                tokens.add(new LiteralToken(cleanedToken));
            }
        }
        return tokens;
    }

    /**
     * Remove leading and trailing non-alphanumeric characters.
     *
     * @param string
     * @return
     */
    private String trimSpecialCharacters(String string) {
        return string.replaceAll(TRIM_SPECIAL_CHARACTERS_PATTERN, "$1");
    }
}
