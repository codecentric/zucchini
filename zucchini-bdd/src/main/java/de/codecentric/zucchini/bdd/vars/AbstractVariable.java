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

import java.util.Map;

public abstract class AbstractVariable<T> implements Variable<T> {
    private final String name;

    /**
     * Initializes a variable with the specified name.
     *
     * @param name The name of the variable.
     */
    protected AbstractVariable(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract T convert(String value);

    /**
     * {@inheritDoc}
     */
    @Override
    public T getConvertedValue(Map<String, String> variables) {
        return convert(variables.get(getName()));
    }
}
