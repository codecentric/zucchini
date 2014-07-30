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

/**
 * Defines common methods that both {@link de.codecentric.zucchini.bdd.resolver.token.LiteralToken} and
 * {@link de.codecentric.zucchini.bdd.resolver.token.VariableToken} need.
 */
abstract class AbstractToken implements Token {
    private String text;

    /**
     * Initializes a token with the specified text.
     *
     * @param text The token text.
     */
    AbstractToken(String text) {
        if (text == null) {
            throw new IllegalArgumentException("The text of a token must not be null.");
        }
        this.text = text;
    }

    /**
     * Returns the text of the token.
     * 
     * This is either (for {@link de.codecentric.zucchini.bdd.resolver.token.VariableToken}) the variable name or (for
     * {@link de.codecentric.zucchini.bdd.resolver.token.LiteralToken}) a literal. Literals can be variable values.
     * 
     * @return The text of the token.
     */
    @Override
    public final String getText() {
        return text;
    }

    /**
     * Checks whether this token equals the given object.
     * 
     * @param o The object that is compared to this token.
     * @return {@literal true} if this token and the given object are equal, and {@literal false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Token)) {
            return false;
        }

        Token that = (Token) o;

        return text != null ? text.equals(that.getText()) : that.getText() == null;
    }

    /**
     * Returns a hash code that reflects the text of the token.
     *
     * @return A hash code that reflects the text of the token.
     */
    @Override
    public int hashCode() {
        return text != null ? text.hashCode() : 0;
    }

    /**
     * Returns a human-readable representation of a token.
     *
     * @return A human-readable representation of a token.
     */
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "text='" + text + '\'' +
                '}';
    }
}
