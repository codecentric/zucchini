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
 * This utility class provides shorthand methods used as variable placeholders.
 */
public class Variables {
    /**
     * Returns a variable that contains a {@link java.lang.String}.
     *
     * @param variableName The name of the variable.
     * @return A variable that contains a stirng.
     */
    public static Variable<String> stringVar(String variableName) {
        return new StringVariable(variableName);
    }

    /**
     * Returns a variable that contains a {@link java.lang.CharSequence} array.
     *
     * @param variableName The name of the variable.
     * @return A variable that contains a {@link java.lang.CharSequence} array.
     */
    public static Variable<CharSequence[]> charSequenceVar(String variableName) {
        return new CharSequenceArrayVariable(variableName);
    }

    /**
     * Returns a variable that contains an {@link java.lang.Integer}.
     *
     * @param variableName The name of the variable.
     * @return A variable that contains an {@link java.lang.Integer}.
     */
    public static Variable<Integer> intVar(String variableName) {
        return new IntegerVariable(variableName);
    }

    /**
     * Returns a variable that contains an {@link java.lang.Long}.
     *
     * @param variableName The name of the variable.
     * @return A variable that contains an {@link java.lang.Long}.
     */
    public static Variable<Long> longVar(String variableName) {
        return new LongVariable(variableName);
    }

    private Variables() {
    }
}
