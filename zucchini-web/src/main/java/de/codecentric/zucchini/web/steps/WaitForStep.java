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

package de.codecentric.zucchini.web.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static de.codecentric.zucchini.bdd.util.Assert.fail;

/**
 * A wait for step waits for a {@link org.openqa.selenium.WebElement element} described by
 * {@link org.openqa.selenium.By}.
 */
public class WaitForStep extends AbstractWebStep {
	private static final Logger logger = LoggerFactory.getLogger(WaitForStep.class);

	private static final long DEFAULT_TIMEOUT = 10;

	private By element;

	private long timeout;

	/**
	 * Initializes a wait for step with a timeout of 10 seconds.
	 *
	 * @param element The element for which shall be waited.
	 */
	public WaitForStep(By element) {
		this(element, DEFAULT_TIMEOUT);
	}

	/**
	 * Initializes a wait for step.
	 *
	 * @param element The element for which shall be waited.
	 * @param timeout The wait timeout in seconds.
	 */
	public WaitForStep(By element, long timeout) {
		this.element = element;
		this.timeout = timeout;
	}

	/**
	 * This method is a convenience method that integrates well into the DSL.
	 *
	 * For example:
	 * <code>
	 * given(...)
	 * .when(waitFor(...).withTimeout(5))
	 * .then(...)
	 * .end();
	 * </code>
	 *
	 * @param timeout The wait timeout in seconds.
	 * @return A wait for step with the specified timeout.
	 */
	public WaitForStep withTimeout(long timeout) {
		this.timeout = timeout;
		return this;
	}

	/**
	 * Waits for the element.
	 */
	@Override
	public void go() {
		logger.info("Waiting {} seconds for {}...", timeout, element);
		WebDriverWait waiting = new WebDriverWait(getWebDriver(), timeout);
		try {
			waiting.until(ExpectedConditions.presenceOfElementLocated(element));
		} catch (NullPointerException e) {
			fail(String.format("Element %s did not appear within %d seconds.", element, timeout), e);
		}
	}
}
