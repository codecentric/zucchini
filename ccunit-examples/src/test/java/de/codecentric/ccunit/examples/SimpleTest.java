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

package de.codecentric.ccunit.examples;

import de.codecentric.ccunit.bdd.ExecutorHolder;
import de.codecentric.ccunit.web.WebDriverExecutor;
import de.codecentric.ccunit.web.provider.ChromeDriverProvider;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static de.codecentric.ccunit.bdd.dsl.TestContext.given;
import static de.codecentric.ccunit.web.conditions.WebConditions.submit;
import static de.codecentric.ccunit.web.conditions.WebConditions.type;
import static de.codecentric.ccunit.web.facts.WebFacts.onPage;
import static de.codecentric.ccunit.web.pageobjects.SimplePage.url;
import static de.codecentric.ccunit.web.results.WebResults.see;

public class SimpleTest {

	@Before
	public void setUp() {
		ExecutorHolder.setExecutor(new WebDriverExecutor(new ChromeDriverProvider()));
	}

	@Test
	public void testSearchOnCodecentricWebsite() {
		By searchInputField = By.name("s");
		given(onPage(url("http://www.codecentric.de")))
				.when(type("codecentric").into(searchInputField))
				.andWhen(submit(searchInputField))
				.then(see("codecentric"))
				.end();
	}

}
