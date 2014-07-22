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

package de.codecentric.zucchini.web;

import org.openqa.selenium.WebDriver;

/**
 * Web driver aware classes will get an instance of the thread-specific {@link org.openqa.selenium.WebDriver}.
 */
public interface WebDriverAware {
	/**
	 * This method is used to inject a {@link org.openqa.selenium.WebDriver} into the class.
	 *
	 * This method is called by the {@link de.codecentric.zucchini.web.WebDriverExecutor}.
	 *
	 * @param webDriver The web driver.
	 */
	void setWebDriver(WebDriver webDriver);
}
