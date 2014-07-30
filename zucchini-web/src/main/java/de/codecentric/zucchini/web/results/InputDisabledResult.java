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

import java.util.Map;

import static de.codecentric.zucchini.bdd.util.Assert.assertNotNull;
import static de.codecentric.zucchini.web.util.WebAssert.findElementOrFail;

/**
 * An input disabled result expects that a specific {@link org.openqa.selenium.WebElement element} described by
 * {@link org.openqa.selenium.By} is a disabled input.
 */
public class InputDisabledResult extends AbstractWebResult {
	private By element;

	/**
	 * Initializes an input disabled result.
	 *
	 * @param element The element whose input state shall be expected to be disabled.
	 */
	public InputDisabledResult(By element) {
		this.element = element;
	}

	/**
	 * Expects that the input state is disabled.
	 */
	@Override
	public void expect() {
		assertNotNull("Element should be disabled but it is not.", findElementOrFail(getWebDriver(), element).getAttribute("disabled"));
	}

    @Override
    public void setVariables(Map<String, String> variables) {
        injectVariables(variables, element);
    }
}