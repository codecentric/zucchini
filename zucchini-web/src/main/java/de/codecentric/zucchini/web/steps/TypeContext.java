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
 * This class is part of the DSL extension and it provides methods to type text either into an
 * {@link org.openqa.selenium.WebElement element} described by {@link org.openqa.selenium.By} or without an element, for
 * example:
 *
 * <code>
 * given(...)
 * .when(type("Lorem ipsum").into(...))
 * .then(...)
 * .end();
 * </code>
 *
 * <code>
 * given(...)
 * .when(type("K"))
 * .then(...)
 * .end();
 * </code>
 *
 * @see de.codecentric.zucchini.web.steps.WebSteps#type(java.lang.CharSequence[])
 * @see de.codecentric.zucchini.web.steps.TypeStep
 */
public class TypeContext extends TypeStep {
	/**
	 * Initializes a type step that types without an element.
	 *
	 * @param keys The keys that shall be typed.
	 */
	public TypeContext(CharSequence... keys) {
		super(keys);
	}

	/**
	 * Returns a type step that types into an {@link org.openqa.selenium.WebElement element} described by
	 * {@link org.openqa.selenium.By}.
	 *
	 * @param into The element into which the keys shall be typed.
	 * @return A type step that types into an element.
	 */
	public TypeIntoStep into(By into) {
		return new TypeIntoStep(getKeys(), into);
	}
}