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

/**
 * This class is part of the DSL extension and it provides methods to define the option that shall be selected.
 *
 * <code>
 * given(...)
 * .when(select(...).index(3))
 * .then(...)
 * .end();
 * </code>
 *
 * @see de.codecentric.zucchini.web.steps.WebSteps#select(org.openqa.selenium.By)
 * @see de.codecentric.zucchini.web.steps.SelectStep
 */
public class SelectContext {
	private final By element;

	/**
	 * Initializes a select context.
	 *
	 * @param element The element whose option that shall be selected.
	 */
	public SelectContext(By element) {
		this.element = element;
	}

	/**
	 * Selects an option by its index.
	 *
	 * @param index The index of the option that shall be selected.
	 * @return A {@link de.codecentric.zucchini.web.steps.SelectStep} that selects an option by its index.
	 */
	public SelectStep index(int index) {
		return new SelectStep(element, index, SelectStep.OptionSelectorType.INDEX);
	}

	/**
	 * Selects an option by its value.
	 *
	 * @param value The value of the option that shall be selected.
	 * @return A {@link de.codecentric.zucchini.web.steps.SelectStep} that selects an option by its value.
	 */
	public SelectStep value(String value) {
		return new SelectStep(element, value, SelectStep.OptionSelectorType.VALUE);
	}

	/**
	 * Selects an option by its text.
	 *
	 * @param text The text of the option that shall be selected.
	 * @return A {@link de.codecentric.zucchini.web.steps.SelectStep} that selects an option by its text.
	 */
	public SelectStep text(String text) {
		return new SelectStep(element, text, SelectStep.OptionSelectorType.TEXT);
	}
}
