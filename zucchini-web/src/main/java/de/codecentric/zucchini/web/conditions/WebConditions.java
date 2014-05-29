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

package de.codecentric.zucchini.web.conditions;

import org.openqa.selenium.By;

public class WebConditions {
	public static TypeContext type(String text) {
		return new TypeContext(text);
	}

	public static SubmitStep submit(By element) {
		return new SubmitStep(element);
	}

	public static ClickStep click(By element) {
		return new ClickStep(element);
	}

	public static WaitForStep waitFor(By element) {
		return new WaitForStep(element);
	}
}
