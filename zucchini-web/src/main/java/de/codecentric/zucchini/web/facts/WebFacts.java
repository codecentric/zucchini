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

package de.codecentric.zucchini.web.facts;

import de.codecentric.zucchini.web.pageobjects.PageObject;

/**
 * This utility class provides methods that return pre-configured web facts.
 */
public class WebFacts {
	/**
	 * Returns a non-operational web fact.
	 *
	 * @return A non-operational web fact.
	 */
	public static NonOperationalWebFact noOpWebFact() {
		return new NonOperationalWebFact();
	}

	/**
	 * Returns an {@link de.codecentric.zucchini.web.facts.OnPageFact} for the given page object.
	 *
	 * You can use this method in conjunction with the static
	 * {@link de.codecentric.zucchini.web.pageobjects.SimplePage#url(String) url()} method of
	 * {@link de.codecentric.zucchini.web.pageobjects.SimplePage}, for example:
	 *
	 * <code>
	 * given(onPage(url("http://www.example.com")))
	 * .when(...)
	 * .then(...)
	 * .end();
	 * </code>
	 *
	 * @param pageObject The page object.
	 * @return An {@link de.codecentric.zucchini.web.facts.OnPageFact} for the given page object.
	 */
	public static WebFact onPage(PageObject pageObject) {
		return new OnPageFact(pageObject);
	}

	/**
	 * Returns an {@link de.codecentric.zucchini.web.facts.OnPageFact} for the given page object class.
	 *
	 * This method is similar to {@link #onPage(de.codecentric.zucchini.web.pageobjects.PageObject) onPage(PageObject)}.
	 *
	 * @param pageObjectClass The page object class.
	 * @return An {@link de.codecentric.zucchini.web.facts.OnPageFact} for the given page object class.
	 */
	public static WebFact onPage(Class<? extends PageObject> pageObjectClass) {
		return new OnPageFact(pageObjectClass);
	}
    
    private WebFacts() {
    }
}
