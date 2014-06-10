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

public class Assert {
	public static void fail(String message) {
		throw new AssertionError(message);
	}

	public static void assertEquals(String message, Object expected, Object actual) {
		if (expected == null && actual != null || !expected.equals(actual)) {
			fail(message);
		}
	}

	public static void assertNotEquals(String message, Object expected, Object actual) {
		if (expected == null && actual == null || expected.equals(actual)) {
			fail(message);
		}
	}

	public static void assertIdentity(String message, Object expected, Object actual) {
		if (expected != actual) {
			fail(message);
		}
	}

	public static void assertNotIdentity(String message, Object expected, Object actual) {
		if (expected == actual) {
			fail(message);
		}
	}

	public static void assertTrue(String message, Object actual) {
		assertIdentity(message, true, actual);
	}

	public static void assertFalse(String message, Object actual) {
		assertIdentity(message, false, actual);
	}

	public static void assertNull(String message, Object actual) {
		assertIdentity(message, null, actual);
	}

	public static void assertNotNull(String message, Object actual) {
		assertNotIdentity(message, null, actual);
	}
}
