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

package de.codecentric.zucchini.web.util;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static de.codecentric.zucchini.bdd.util.Assert.fail;

/**
 * This utility class provides methods similar to JUnit's assertion methods but with a focus on web-specific assertions.
 */
public class WebAssert {
	/**
	 * Tries to find a specific element and fails if the element could not be found.
	 *
	 * @param webDriver The web driver.
	 * @param element   The element.
	 * @return The found element.
	 */
	public static WebElement findElementOrFail(WebDriver webDriver, By element) {
		try {
			return webDriver.findElement(element);
		} catch (NoSuchElementException e) {
			fail("Element should exist but it does not.", e);
		}
		/**
		 * Never reached since {@link de.codecentric.zucchini.bdd.util.Assert#fail(String)} fail()} throws
		 * {@link java.lang.AssertionError}.
		 */
		return null;
	}

	private WebAssert() {
	}
}
