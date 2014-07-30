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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;

import static de.codecentric.zucchini.bdd.dsl.impl.ScenarioBuilder.given;
import static de.codecentric.zucchini.bdd.vars.Variables.charSequenceVar;
import static de.codecentric.zucchini.bdd.vars.Variables.stringVar;
import static de.codecentric.zucchini.web.facts.WebFacts.onPage;
import static de.codecentric.zucchini.web.results.WebResults.see;
import static de.codecentric.zucchini.web.steps.WebSteps.submit;
import static de.codecentric.zucchini.web.steps.WebSteps.type;
import static de.codecentric.zucchini.web.vars.ParameterizedBy.element;
import static de.codecentric.zucchini.web.vars.WebVariables.pageVar;

public class VariablesTest {
	@Rule
	public WebDriverExecutorRule webDriverExecutorRule = new WebDriverExecutorRule(new ChromeDriverProvider());

	@Rule
	public WebFactRule onCodecentricRule = new WebFactRule(
            "I am on \"${url}\"",
            onPage(pageVar("url"))
    );

	@Rule
	public WebStepRule searchCodecentricRule = new WebStepRule(
            "I type ${text} into the input field with the name ${name}",
            type(charSequenceVar("text")).into(element(By.ByName.class, "name"))
    );

	@Rule
	public WebStepRule submitSearchRule = new WebStepRule(
            "I submit the input field with the name ${name}", submit(element(By.ByName.class, "name"))
    );

	@Rule
	public WebResultRule seeCodecentricOnPageRule = new WebResultRule(
            "I see \"${text}\" on the page", see(stringVar("text"))
    );

	@Rule
	public WebFactRule preparedFactRule = new WebFactRule(
            "Open ${url}, type ${text} into ${name} and expect the same text.",
            given(onPage(pageVar("url")))
			.when(type(charSequenceVar("text")).into(element(By.ByName.class, "name")))
			.andWhen(submit(element(By.ByName.class, "name")))
			.then(see(stringVar("text")))
			.asFact()
    );

    @BeforeClass
    public static void setUp() throws Exception {
        StatementResolverHolder.setStatementResolver(new VariableStatementResolver());
    }

    @Test
	public void testRules() {
		given("I am on \"http://www.codecentric.de\"")
				.when("I type codecentric into the input field with the name s")
				.andWhen("I submit the input field with the name s")
				.then("I see \"codecentric\" on the page")
				.end();
	}

	@Test
	public void testPreparedRules() {
		given("Open \"http://www.codecentric.de\", type codecentric into s and expect the same text.").end();
	}
}
