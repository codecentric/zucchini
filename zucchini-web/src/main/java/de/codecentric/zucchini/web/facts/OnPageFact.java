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

import de.codecentric.zucchini.web.pageobjects.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OnPageFact implements WebFact {
	private static final Logger logger = LoggerFactory.getLogger(OnPageFact.class);

	private WebDriver webDriver;

	private PageObject pageObject;

	private Class<? extends PageObject> pageObjectClass;

	private boolean isInitialized = false;

	public OnPageFact(PageObject pageObject) {
		if (pageObject == null) {
			throw new NullPointerException("You must specify a valid page object or page object class.");
		}
		this.pageObject = pageObject;
	}

	public OnPageFact(Class<? extends PageObject> pageObjectClass) {
		if (pageObjectClass == null) {
			throw new NullPointerException("You must specify a valid page object or page object class.");
		}
		this.pageObjectClass = pageObjectClass;
	}

	@Override
	public void establish() {
		logger.info("Opening page {}...", pageObject);
		pageObject.open();
	}

	@Override
	public void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
		initializePage();
		pageObject.setWebDriver(webDriver);
	}

	private void initializePage() {
		if (!isInitialized) {
			if (pageObject != null) {
				PageFactory.initElements(webDriver, pageObject);
			} else if (pageObjectClass != null) {
				pageObject = PageFactory.initElements(webDriver, pageObjectClass);
			}
		}
	}
}
