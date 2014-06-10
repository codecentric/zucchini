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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static de.codecentric.zucchini.web.util.WebAssert.findElementOrFail;

public class ClickStep extends AbstractWebStep {
	private static final Logger logger = LoggerFactory.getLogger(ClickStep.class);

	private By element;

	public ClickStep(By element) {
		this.element = element;
	}

	@Override
	public void go() {
		logger.info("Clicking {}...", element);
		findElementOrFail(getWebDriver(), element).click();
	}
}
