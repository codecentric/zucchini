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

package de.codecentric.zucchini.bdd.vars;

/**
 * A variable representing a {@link java.lang.String}.
 */
public class StringVariable extends AbstractVariable<String> {
    /**
     * Initializes a string variable.
     *
     * @param name The name of the variable.
     */
    public StringVariable(String name) {
        super(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String convert(String value) {
        return value;
    }
}
