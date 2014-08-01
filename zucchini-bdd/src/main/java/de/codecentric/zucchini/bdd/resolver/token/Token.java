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
 * This interface describes a part of a statement name that is either a
 * {@link de.codecentric.zucchini.bdd.resolver.token.VariableToken variable} or a
 * {@link de.codecentric.zucchini.bdd.resolver.token.LiteralToken literal}.
 *
 * @see de.codecentric.zucchini.bdd.resolver.token.AbstractToken
 * @see de.codecentric.zucchini.bdd.resolver.token.VariableToken
 * @see de.codecentric.zucchini.bdd.resolver.token.LiteralToken
 */
@SuppressWarnings("WeakerAccess")
public interface Token {
    /**
     * Returns the token text.
     * 
     * @return The token text.
     */
    String getText();
}
