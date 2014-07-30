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
import java.util.HashMap;
import java.util.ListIterator;
import java.util.Map;

/**
 * A token list is a specialized {@link java.util.ArrayList} that provides mechanisms for variable value detection,
 * validation and comparison.
 */
public final class TokenList extends ArrayList<Token> {
    /**
     * Detects variable values using another token list.
     *
     * Usually, one is working with two token lists. One list contains literals and variables and the other token list
     * contains only literals. Both lists match are then compared to find values (literals of the second list) for
     * variables in the first list.
     *
     * Example:
     * - Token list 1: "a", "b", var "x", var "y"
     * - Token list 2: "a", "b", "c", "d"
     *
     * The detected variables are:
     * - variable["x"] = "c"
     * - variable["y"] = "d"
     *
     * Note: Only one list may contain zero or more variables. An exception is thrown if both lists contain variables.
     *
     * @param tokenList The other token list.
     * @return A map containing variable names and variable values.
     * @throws de.codecentric.zucchini.bdd.resolver.token.VariableValueDetectionException Thrown if both lists contain
     *                                                                                    variables.
     */
    public Map<String, String> detectVariableValues(TokenList tokenList) {
        Map<String, String> variables = new HashMap<String, String>();

        boolean thisContainsVariables = containsVariables();
        boolean thatContainsVariables = tokenList.containsVariables();

        // If both lists contain variables, we get into trouble.
        if (thisContainsVariables && thatContainsVariables) {
            throw new VariableValueDetectionException("Cannot detect variable values if both token lists contain variables.");
        }

        // If both lists don't contain variables, no variables can be detected and we can return the empty map.
        if (!thisContainsVariables && !thatContainsVariables) {
            return variables;
        }

        ListIterator<Token> iteratorWithVariables = (thisContainsVariables ? this : tokenList).listIterator();
        ListIterator<Token> iteratorWithoutVariables = (thisContainsVariables ? tokenList : this).listIterator();

        // We now know that {@code iteratorWithVariables} contains at least one variable. We iterate over both lists
        // simultaneously and if {@code possibleVariableToken} is a {@link VariableToken}, we know that
        // {@code literalToken} contains the value for this variable.
        while (iteratorWithVariables.hasNext() && iteratorWithoutVariables.hasNext()) {
            Token possibleVariableToken = iteratorWithVariables.next();
            Token literalToken = iteratorWithoutVariables.next();

            if (possibleVariableToken instanceof VariableToken) {
                variables.put(possibleVariableToken.getText(), literalToken.getText());
            }
        }
        return variables;
    }

    /**
     * Checks whether this token list contains a {@link de.codecentric.zucchini.bdd.resolver.token.VariableToken} with
     * the specified name.
     *
     * @param name The name to check.
     * @return {@literal true} if this token list contains a
     * {@link de.codecentric.zucchini.bdd.resolver.token.VariableToken} with the specified name, and {@literal false}
     * otherwise.
     */
    public boolean containsVariableWithName(String name) {
        for (Token token : this) {
            if (token instanceof VariableToken && token.getText().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether this list is equal to the given object.
     *
     * Two token lists are considered equal if...
     * - all tokens are equal, i.e. the token texts, token types and number of tokens match.
     * - one list only contains literals and the other list contains any combination of literals and variables but the
     * literals match.
     *
     * Examples for equal lists:
     * - "a", "b", var "x" == "a", "b", "c"
     * - var "x", var "y" == var "x", var "y"
     *
     * Note: This method does not use {@link #containsVariables()} due to performance optimizations.
     *
     * @param o The other list.
     * @return {@literal true} if the lists are equal and {@literal false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof TokenList)) {
            return false;
        }

        TokenList that = (TokenList) o;
        ListIterator<Token> thisIterator = listIterator();
        ListIterator<Token> thatIterator = that.listIterator();
        boolean onlyOneListContainsVariables = false;
        boolean thisListContainsVariables = false;
        while (thisIterator.hasNext() && thatIterator.hasNext()) {
            Token thisToken = thisIterator.next();
            Token thatToken = thatIterator.next();

            // Either both tokens are null or none of them is.
            if (thisToken == null && thatToken != null || thisToken != null && thatToken == null) {
                return false;
            }

            // If this token is a variable and that token is not, only this list may contain further variables.
            if (thisToken instanceof VariableToken && thatToken instanceof LiteralToken) {
                if (onlyOneListContainsVariables && !thisListContainsVariables) {
                    return false;
                }

                onlyOneListContainsVariables = true;
                thisListContainsVariables = true;
            }

            // If that token is a variable and this token is not, only that list may contain further variables.
            if (thatToken instanceof VariableToken && thisToken instanceof LiteralToken) {
                if (onlyOneListContainsVariables && thisListContainsVariables) {
                    return false;
                }

                onlyOneListContainsVariables = true;
                thisListContainsVariables = false;
            }

            // If both tokens are variables, then both variable names must match.
            if (tokenTypesMatch(thisToken, thatToken, VariableToken.class) && tokenTextsDoNotMatch(thisToken, thatToken)) {
                return false;
            }

            // If both tokens are literals, then both names must match.
            if (tokenTypesMatch(thisToken, thatToken, LiteralToken.class) && tokenTextsDoNotMatch(thisToken, thatToken)) {
                return false;
            }
        }
        return !(thisIterator.hasNext() || thatIterator.hasNext());
    }

    /**
     * Checks whether this token list contains variables.
     *
     * @return {@literal true} if this list contains variables, and {@literal false} otherwise.
     */
    private boolean containsVariables() {
        for (Token token : this) {
            if (token instanceof VariableToken) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether both tokens have the specified type.
     *
     * @param token1    The first token to check.
     * @param token2    The second token to check.
     * @param tokenType The type that both tokens must have.
     * @return {@literal true} if both tokens have the specified type, and {@literal false} otherwise.
     */
    private boolean tokenTypesMatch(Token token1, Token token2, Class<? extends Token> tokenType) {
        return tokenType.isAssignableFrom(token1.getClass()) && tokenType.isAssignableFrom(token2.getClass());
    }

    /**
     * Checks whether both tokens have the same texts.
     *
     * @param token1 The first token to check.
     * @param token2 The second token to check.
     * @return {@literal true} if the tokens do not have the same texts, and {@literal false} otherwise.
     */
    private boolean tokenTextsDoNotMatch(Token token1, Token token2) {
        return !token1.getText().equals(token2.getText());
    }
}
