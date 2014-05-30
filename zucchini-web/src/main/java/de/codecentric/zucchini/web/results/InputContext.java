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

public class InputContext {
	private final By element;

	public InputContext(By element) {
		this.element = element;
	}

	public InputReadOnlyResult isReadOnly() {
		return new InputReadOnlyResult(this);
	}

	public InputDisabledResult isDisabled() {
		return new InputDisabledResult(this);
	}

	public InputValueResult value(String value) {
		return new InputValueResult(this, value);
	}

	By getElement() {
		return element;
	}
}
