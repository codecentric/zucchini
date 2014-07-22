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

package de.codecentric.zucchini.web.results;


import org.openqa.selenium.By;

import static de.codecentric.zucchini.bdd.util.Assert.assertNotNull;
import static de.codecentric.zucchini.web.util.WebAssert.findElementOrFail;

/**
 * An input read-only result expects that a specific {@link org.openqa.selenium.WebElement element} described by
 * {@link org.openqa.selenium.By} is a read-only input.
 */
public class InputReadOnlyResult extends AbstractWebResult {
	private By element;

	/**
	 * Initializes an input read-only result.
	 *
	 * @param element The element whose input state shall be expected to be read-only.
	 */
	public InputReadOnlyResult(By element) {
		this.element = element;
	}

	/**
	 * Expects that the input state is read-only.
	 */
	@Override
	public void expect() {
		assertNotNull("Element should be read-only but it is not.", findElementOrFail(getWebDriver(), element).getAttribute("readonly"));
	}
}