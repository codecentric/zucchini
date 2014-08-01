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

package de.codecentric.zucchini.web.junit;

import de.codecentric.zucchini.web.WebDriverExecutor;
import de.codecentric.zucchini.web.provider.ChromeDriverProvider;
import org.junit.Test;
import org.openqa.selenium.By;

import static de.codecentric.zucchini.bdd.dsl.impl.ScenarioBuilder.given;
import static de.codecentric.zucchini.web.facts.WebFacts.onPage;
import static de.codecentric.zucchini.web.pageobjects.SimplePage.url;
import static de.codecentric.zucchini.web.results.WebResults.see;
import static de.codecentric.zucchini.web.steps.WebSteps.submit;
import static de.codecentric.zucchini.web.steps.WebSteps.type;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public abstract class ZucchiniWebRuleTestBase {
	@Test
	public void testExecuteTestWithAutoConfiguredZucchiniWebRule() {
		By searchInputField = By.name("s");
		given(onPage(url("http://www.codecentric.de")))
				.when(type("codecentric").into(searchInputField))
				.andWhen(submit(searchInputField))
				.then(see("codecentric"))
				.end();
	}

	@Test
	public void testWebDriverProviderIsConfigured() {
		assertNotNull(getWebDriverExecutor());
		assertTrue(getWebDriverExecutor().getWebDriverProvider() instanceof ChromeDriverProvider);
	}

	protected abstract WebDriverExecutor getWebDriverExecutor();
}
