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

public class OnPageFact implements WebFact {
	private WebDriver webDriver;
	private PageObject pageObject;

	public OnPageFact(PageObject pageObject) {
		this.pageObject = pageObject;
		PageFactory.initElements(webDriver, pageObject);
	}

	public OnPageFact(Class<? extends PageObject> pageObjectClass) {
		this.pageObject = PageFactory.initElements(webDriver, pageObjectClass);
	}

	@Override
	public void establish() {
		pageObject.open();
	}

	@Override
	public void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
		pageObject.setWebDriver(webDriver);
	}
}
