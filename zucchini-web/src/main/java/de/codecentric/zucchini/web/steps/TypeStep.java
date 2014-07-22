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

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A type into step types keys (text or a key combination).
 *
 * @see de.codecentric.zucchini.web.steps.WebSteps#type(java.lang.CharSequence[])
 */
public class TypeStep extends AbstractWebStep {
	private static final Logger logger = LoggerFactory.getLogger(TypeStep.class);

	private CharSequence[] keys;

	/**
	 * Initializes a type step.
	 *
	 * @param keys The keys that shall be typed.
	 */
	public TypeStep(CharSequence... keys) {
		this.keys = keys;
	}

	CharSequence[] getKeys() {
		return keys;
	}

	/**
	 * Types the keys.
	 */
	@Override
	public void go() {
		logger.info("Typing \"{}\"...", keys);
		new Actions(getWebDriver()).sendKeys(Keys.ENTER);
	}
}
