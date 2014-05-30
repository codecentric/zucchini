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

import org.openqa.selenium.support.ui.Select;

public class SelectStep extends AbstractWebStep {

	public enum OptionSelectorType {
		VALUE,
		TEXT,
		INDEX
	}

	private SelectContext selectContext;

	private Object optionSelector;

	private OptionSelectorType optionSelectorType;

	public SelectStep(SelectContext selectContext, Object optionSelector, OptionSelectorType optionSelectorType) {
		this.selectContext = selectContext;
		this.optionSelector = optionSelector;
		this.optionSelectorType = optionSelectorType;
	}

	@Override
	public void go() {
		Select select = new Select(getWebDriver().findElement(selectContext.getElement()));
		if (OptionSelectorType.INDEX.equals(optionSelectorType)) {
			select.selectByIndex((Integer) optionSelector);
		} else if (OptionSelectorType.VALUE.equals(optionSelectorType)) {
			select.selectByValue((String) optionSelector);
		} else if (OptionSelectorType.TEXT.equals(optionSelectorType)) {
			select.selectByVisibleText((String) optionSelector);
		}
	}
}
