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

package de.codecentric.zucchini.examples;

import de.codecentric.zucchini.bdd.resolver.StatementResolverHolder;
import de.codecentric.zucchini.bdd.resolver.VariableStatementResolver;
import de.codecentric.zucchini.web.junit.WebDriverExecutorRule;
import de.codecentric.zucchini.web.junit.WebFactRule;
import de.codecentric.zucchini.web.junit.WebResultRule;
import de.codecentric.zucchini.web.junit.WebStepRule;
import de.codecentric.zucchini.web.provider.ChromeDriverProvider;
import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;

import static de.codecentric.zucchini.bdd.dsl.impl.ScenarioBuilder.given;
import static de.codecentric.zucchini.web.facts.WebFacts.onPage;
import static de.codecentric.zucchini.web.pageobjects.SimplePage.url;
import static de.codecentric.zucchini.web.results.WebResults.see;
import static de.codecentric.zucchini.web.steps.WebSteps.submit;
import static de.codecentric.zucchini.web.steps.WebSteps.type;

public class StatementRuleTest {
	@Rule
	public WebDriverExecutorRule webDriverExecutorRule = new WebDriverExecutorRule(new ChromeDriverProvider());

	@Rule
	public WebFactRule onCodecentricRule = new WebFactRule("I am on codecentric.de", onPage(url("http://www.codecentric.de")));

	@Rule
	public WebStepRule searchCodecentricRule = new WebStepRule("I type codecentric into the search box", type("codecentric").into(By.name("s")));

	@Rule
	public WebStepRule submitSearchRule = new WebStepRule("I submit the search", submit(By.name("s")));

	@Rule
	public WebResultRule seeCodecentricOnPageRule = new WebResultRule("I see codecentric on the page", see("codecentric"));

	@Rule
	public WebFactRule preparedFactRule = new WebFactRule("all", given(onPage(url("http://www.codecentric.de")))
			.when(type("codecentric").into(By.name("s")))
			.andWhen(submit(By.name("s")))
			.then(see("codecentric"))
			.asFact());


    /**
     * Reset the variable statement resolver so that statements registered within this test class are removed.
     *
     * Usually, this is not necessary.
     */
    @AfterClass
    public static void tearDown() {
        StatementResolverHolder.setStatementResolver(new VariableStatementResolver());
    }

	@Test
	public void testRules() {
		given("I am on codecentric.de")
				.when("I type codecentric into the search box")
				.andWhen("I submit the search")
				.then("I see codecentric on the page")
				.end();
	}

	@Test
	public void testPreparedRules() {
		given("all").end();
	}
}
