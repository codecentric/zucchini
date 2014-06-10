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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static de.codecentric.zucchini.web.util.WebAssert.findElementOrFail;

public class SelectStep extends AbstractWebStep {
	private static final Logger logger = LoggerFactory.getLogger(SelectStep.class);

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
		logger.info("Waiting for select {}...", selectContext.getElement());
		Select select = new Select(findElementOrFail(getWebDriver(), selectContext.getElement()));
		if (OptionSelectorType.INDEX.equals(optionSelectorType)) {
			logger.info("Selecting index {}...", optionSelector);
			select.selectByIndex((Integer) optionSelector);
		} else if (OptionSelectorType.VALUE.equals(optionSelectorType)) {
			logger.info("Selecting value {}...", optionSelector);
			select.selectByValue((String) optionSelector);
		} else if (OptionSelectorType.TEXT.equals(optionSelectorType)) {
			logger.info("Selecting text {}...", optionSelector);
			select.selectByVisibleText((String) optionSelector);
		}
	}
}
