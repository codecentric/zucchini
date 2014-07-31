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

package de.codecentric.zucchini.bdd.util;

/**
 * This utility class provides methods similar to JUnit's assertion methods. This class exists since this framework
 * should be independent from other testing frameworks,
 */
public class Assert {
    /**
     * Throws an {@link java.lang.AssertionError}.
     *
     * @param message The message of the thrown {@link java.lang.AssertionError}.
     */
    public static void fail(String message) {
        throw new AssertionError(message);
    }

    /**
     * Throws an {@link java.lang.AssertionError}.
     *
     * @param message The message of the thrown {@link java.lang.AssertionError}.
     * @param cause   The exception that caused this failure.
     */
    public static void fail(String message, Exception cause) {
        throw new AssertionError(message, cause);
    }

    /**
     * Asserts that two objects are equal, i.e. {@code expected.equals(actual) == true}, and fails otherwise.
     *
     * @param message  The message of the thrown {@link java.lang.AssertionError}.
     * @param expected The expected object.
     * @param actual   The actual object.
     */
    public static void assertEquals(String message, Object expected, Object actual) {
        if (expected == null && actual != null || expected != null && !expected.equals(actual)) {
            fail(message);
        }
    }

    /**
     * Asserts that two objects are not equal, i.e. {@code expected.equals(actual) == false}, and fails otherwise.
     *
     * @param message  The message of the thrown {@link java.lang.AssertionError}.
     * @param expected The expected object.
     * @param actual   The actual object.
     */
    public static void assertNotEquals(String message, Object expected, Object actual) {
        if (expected == null && actual == null || expected != null && expected.equals(actual)) {
            fail(message);
        }
    }

    /**
     * Asserts that two objects have the same identity, i.e. {@code expected == actual}, and fails otherwise.
     *
     * @param message  The message of the thrown {@link java.lang.AssertionError}.
     * @param expected The expected object.
     * @param actual   The actual object.
     */
    @SuppressWarnings("WeakerAccess")
    public static void assertIdentity(String message, Object expected, Object actual) {
        if (expected != actual) {
            fail(message);
        }
    }

    /**
     * Asserts that two objects don't have the same identity, i.e. {@code expected != actual}, and fails otherwise.
     *
     * @param message  The message of the thrown {@link java.lang.AssertionError}.
     * @param expected The expected object.
     * @param actual   The actual object.
     */
    @SuppressWarnings({"SameParameterValue", "WeakerAccess"})
    public static void assertNotIdentity(String message, Object expected, Object actual) {
        if (expected == actual) {
            fail(message);
        }
    }

    /**
     * Asserts that the actual value is {@literal true}, i.e. {@code actual == true}, and fails otherwise.
     *
     * @param message The message of the thrown {@link java.lang.AssertionError}.
     * @param actual  The actual object.
     */
    public static void assertTrue(String message, Object actual) {
        assertIdentity(message, true, actual);
    }

    /**
     * Asserts that the actual value is {@literal false}, i.e. {@code actual == false}, and fails otherwise.
     *
     * @param message The message of the thrown {@link java.lang.AssertionError}.
     * @param actual  The actual object.
     */
    public static void assertFalse(String message, Object actual) {
        assertIdentity(message, false, actual);
    }

    /**
     * Asserts that the actual value is {@literal null}, i.e. {@code actual == null}, and fails otherwise.
     *
     * @param message The message of the thrown {@link java.lang.AssertionError}.
     * @param actual  The actual object.
     */
    public static void assertNull(String message, Object actual) {
        assertIdentity(message, null, actual);
    }

    /**
     * Asserts that the actual value is not {@literal null}, i.e. {@code actual != null}, and fails otherwise.
     *
     * @param message The message of the thrown {@link java.lang.AssertionError}.
     * @param actual  The actual object.
     */
    public static void assertNotNull(String message, Object actual) {
        assertNotIdentity(message, null, actual);
    }

    private Assert() {
    }
}
