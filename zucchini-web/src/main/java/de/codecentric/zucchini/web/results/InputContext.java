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

package de.codecentric.zucchini.web.results;

import org.openqa.selenium.By;

/**
 * This class is part of the DSL extension and it provides methods to define the input state that shall be expected.
 *
 * <code>
 * given(...)
 * .when(...)
 * .then(input(...).isReadOnly())
 * .end();
 * </code>
 *
 * @see de.codecentric.zucchini.web.results.WebResults#input(org.openqa.selenium.By)
 * @see de.codecentric.zucchini.web.results.InputReadOnlyResult
 * @see de.codecentric.zucchini.web.results.InputDisabledResult
 * @see de.codecentric.zucchini.web.results.InputValueResult
 */
public class InputContext {
	private final By element;

	/**
	 * Initializes an input context.
	 *
	 * @param element The element whose input state shall be expected.
	 */
	public InputContext(By element) {
		this.element = element;
	}

	/**
	 * Returns an {@link de.codecentric.zucchini.web.results.InputReadOnlyResult input read-only result}.
	 *
	 * @return An {@link de.codecentric.zucchini.web.results.InputReadOnlyResult input read-only result}.
	 */
	public InputReadOnlyResult isReadOnly() {
		return new InputReadOnlyResult(element);
	}

	/**
	 * Returns an {@link de.codecentric.zucchini.web.results.InputDisabledResult input disabled result}.
	 *
	 * @return An {@link de.codecentric.zucchini.web.results.InputDisabledResult input disabled result}.
	 */
	public InputDisabledResult isDisabled() {
		return new InputDisabledResult(element);
	}

	/**
	 * Returns an {@link de.codecentric.zucchini.web.results.InputValueResult input value result}.
	 *
	 * @param value The input value that shall be expected.
	 * @return An {@link de.codecentric.zucchini.web.results.InputValueResult input value result}.
	 */
	public InputValueResult value(String value) {
		return new InputValueResult(element, value);
	}
}
