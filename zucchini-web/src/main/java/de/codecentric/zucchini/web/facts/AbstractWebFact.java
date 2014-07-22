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

package de.codecentric.zucchini.web.facts;

import org.openqa.selenium.WebDriver;

/**
 * The abstract web fact simplifies the usage implementation of
 * {@link de.codecentric.zucchini.web.facts.WebFact web facts} by providing common methods.
 */
public abstract class AbstractWebFact implements WebFact {
	private WebDriver webDriver;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	/**
	 * Returns an instance of the {@link org.openqa.selenium.WebDriver web driver} of the current thread.
	 *
	 * @return An instance of the web driver of the current thread.
	 */
	protected final WebDriver getWebDriver() {
		return webDriver;
	}
}
