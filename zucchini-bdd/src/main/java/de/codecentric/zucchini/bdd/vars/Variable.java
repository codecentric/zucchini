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

/**
 * This interface represents all kinds of variables that can be injected into
 * {@link de.codecentric.zucchini.bdd.dsl.Statement statements}.
 * @param <T> The actual type of the variable.
 */
public interface Variable<T> {
    /**
     * Returns the name of the variable.
     * @return The name of the variable.
     */
    String getName();

    /**
     * Converts the given value to a representation according to the type of the variable.
     * 
     * @param value The value that shall be converted.
     * @return A representation of the given value.
     */
    T convert(String value);

    /**
     * Retrieves the value of the variable from the given variable value map, converts it and returns the result.
     * 
     * @param variables A map of variable names and values.
     * @return A representation fo the variable value.
     */
    T getConvertedValue(Map<String, String> variables);
}
