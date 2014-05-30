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

package de.codecentric.zucchini.web.provider;

import org.openqa.selenium.WebDriver;

public abstract class AbstractWebDriverProvider implements WebDriverProvider {
	private WebDriver webDriver;

	private int nestingLevel = 0;

	@Override
	public void startWebDriver() {
		if (webDriver == null) {
			webDriver = createWebDriver();
		}
		nestingLevel++;
	}

	@Override
	public void stopWebDriver() {
		nestingLevel--;
		if (webDriver != null && nestingLevel == 0) {
			webDriver.close();
			webDriver.quit();
			webDriver = null;
		}
	}

	@Override
	public WebDriver getWebDriver() {
		return webDriver;
	}

	protected abstract WebDriver createWebDriver();
}
