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
 * This utility class provides methods that return pre-configured web steps.
 */
public class WebSteps {
	/**
	 * Returns a {@link de.codecentric.zucchini.web.steps.TypeContext type context}.
	 *
	 * @param keys The keys that shall be typed.
	 * @return a {@link de.codecentric.zucchini.web.steps.TypeContext type context}.
	 * @see de.codecentric.zucchini.web.steps.TypeStep
	 */
	public static TypeContext type(CharSequence... keys) {
		return new TypeContext(keys);
	}

	/**
	 * Returns a non-operational web step.
	 *
	 * @return A non-operational web step.
	 */
	public static NonOperationalWebStep noOpWebStep() {
		return new NonOperationalWebStep();
	}

	/**
	 * Returns a {@link de.codecentric.zucchini.web.steps.SelectContext select context}.
	 *
	 * @param element The element with options.
	 * @return A {@link de.codecentric.zucchini.web.steps.SelectContext select context}.
	 * @see de.codecentric.zucchini.web.steps.SelectStep
	 * @see de.codecentric.zucchini.web.steps.SelectContext
	 */
	public static SelectContext select(By element) {
		return new SelectContext(element);
	}

	/**
	 * Returns a {@link de.codecentric.zucchini.web.steps.SubmitStep submit step}.
	 *
	 * @param element The element that shall be submitted.
	 * @return A {@link de.codecentric.zucchini.web.steps.SubmitStep submit step}.
	 */
	public static SubmitStep submit(By element) {
		return new SubmitStep(element);
	}

	/**
	 * Returns a {@link de.codecentric.zucchini.web.steps.ClearStep clear step}.
	 *
	 * @param element The element that shall be cleared.
	 * @return A {@link de.codecentric.zucchini.web.steps.ClearStep clear step}.
	 */
	public static ClearStep clear(By element) {
		return new ClearStep(element);
	}

	/**
	 * Returns a {@link de.codecentric.zucchini.web.steps.ClickStep click step}.
	 *
	 * @param element The element that shall be clicked.
	 * @return A {@link de.codecentric.zucchini.web.steps.ClickStep click step}.
	 */
	public static ClickStep click(By element) {
		return new ClickStep(element);
	}

	/**
	 * Returns a {@link de.codecentric.zucchini.web.steps.WaitStep wait step}.
	 *
	 * @return A {@link de.codecentric.zucchini.web.steps.WaitStep wait step}.
	 */
	public static WaitStep doWait() {
		return new WaitStep();
	}

	/**
	 * Returns a {@link de.codecentric.zucchini.web.steps.WaitStep wait step}.
	 *
	 * @param sleepTime The wait timeout in milliseconds.
	 * @return A {@link de.codecentric.zucchini.web.steps.WaitStep wait step}.
	 */
	public static WaitStep doWait(long sleepTime) {
		return new WaitStep(sleepTime);
	}

	/**
	 * Returns a {@link de.codecentric.zucchini.web.steps.WaitForStep wait for step}.
	 *
	 * @param element The element for which shall be waited.
	 * @return A {@link de.codecentric.zucchini.web.steps.WaitForStep wait for step}.
	 */
	public static WaitForStep waitFor(By element) {
		return new WaitForStep(element);
	}

	/**
	 * Returns a {@link de.codecentric.zucchini.web.steps.WaitForStep wait for step}.
	 *
	 * @param element The element for which shall be waited.
	 * @param timeout The wait timeout in seconds.
	 * @return A {@link de.codecentric.zucchini.web.steps.WaitForStep wait for step}.
	 */
	public static WaitForStep waitFor(By element, long timeout) {
		return new WaitForStep(element, timeout);
	}

	private WebSteps() {
	}
}
