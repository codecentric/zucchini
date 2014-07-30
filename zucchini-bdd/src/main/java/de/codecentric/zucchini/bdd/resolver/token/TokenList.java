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

public final class TokenList extends ArrayList<Token> {
    public Map<String, String> detectVariableValues(TokenList tokenList) {
        boolean thisContainsVariables = containsVariables();
        boolean thatContainsVariables = tokenList.containsVariables();
        Map<String, String> variables = new HashMap<String, String>();

        // If both lists contain variables, we get into trouble.
        if (thisContainsVariables && thatContainsVariables) {
            throw new RuntimeException("Cannot detect variables if both token lists contain variables.");
        }

        // If both lists don't contain variables, no variables can be detected and we can return the empty map.
        if (!thisContainsVariables && !thatContainsVariables) {
            return variables;
        }

        ListIterator<Token> iteratorWithVariables = (thisContainsVariables ? this : tokenList).listIterator();
        ListIterator<Token> iteratorWithoutVariables = (thisContainsVariables ? tokenList : this).listIterator();

        while (iteratorWithVariables.hasNext() && iteratorWithoutVariables.hasNext()) {
            Token possibleVariableToken = iteratorWithVariables.next();
            Token literalToken = iteratorWithoutVariables.next();
            if (possibleVariableToken instanceof VariableToken) {
                variables.put(possibleVariableToken.getText(), literalToken.getText());
            }
        }
        return variables;
    }

    public boolean containsVariables() {
        for (Token token : this) {
            if (token instanceof VariableToken) {
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
     * This method does not use {@link #containsVariables()} due to performance optimizations.
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
            if (thisToken instanceof VariableToken && thatToken instanceof VariableToken && !thisToken.getText().equals(thatToken.getText())) {
                return false;
            }

            // If both tokens are literals, then both names must match.
            if (thisToken instanceof LiteralToken && thatToken instanceof LiteralToken && !thisToken.getText().equals(thatToken.getText())) {
                return false;
            }
        }
        return !(thisIterator.hasNext() || thatIterator.hasNext());
    }
}
