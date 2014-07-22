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

package de.codecentric.zucchini.web.pageobjects;

import de.codecentric.zucchini.web.WebDriverAware;

/**
 * A Page object is a design pattern which is commonly used with Selenium tests.
 *
 * @see <a href="https://code.google.com/p/selenium/wiki/PageObjects">Page Objects</a>
 */
public interface PageObject extends WebDriverAware {
	/**
	 * The only method that all page objects must implement is used to open the page in a browser by navigating to its
	 * URL.
	 */
	void open();
}
