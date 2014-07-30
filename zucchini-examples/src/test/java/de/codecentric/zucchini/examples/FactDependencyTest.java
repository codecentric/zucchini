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
import de.codecentric.zucchini.web.provider.ChromeDriverProvider;
import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import static de.codecentric.zucchini.bdd.dsl.impl.ScenarioBuilder.given;
import static de.codecentric.zucchini.web.facts.WebFacts.onPage;
import static de.codecentric.zucchini.web.pageobjects.SimplePage.url;

public class FactDependencyTest {
	@Rule
	public WebDriverExecutorRule webDriverExecutorRule = new WebDriverExecutorRule(new ChromeDriverProvider());

	@Rule
	public WebFactRule onCodecentricRule = new WebFactRule("I am on codecentric.de", onPage(url("http://www.codecentric.de")));

	@Rule
	public WebFactRule onCodecentricBlogRule = new WebFactRule("I am on blog.codecentric.de", onPage(url("http://blog.codecentric.de")));

	@Rule
	public WebFactRule visitCodecentricAndCodecentricBlogFact = new WebFactRule(
			"I visit codecentric.de and blog.codecentric.de",
			given("I am on codecentric.de")
					.andGiven("I am on blog.codecentric.de").asFact()
	);

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
	public void testVisitCodecentricAndCodecentricBlogWithoutDoingAnything() {
		createShortcutFact();
		given("I visit (blog.)codecentric.de").end();
	}

	/**
	 * This might be fixed in an upcoming release.
	 */
	@Ignore
	@Test
	public void testCycleDetection() {
		given("B").registerAsFact("A");
		given("C").registerAsFact("B");
		given("A").registerAsFact("C");
		given("A").end();
	}

	private void createShortcutFact() {
		given("I visit codecentric.de and blog.codecentric.de").registerAsFact("I visit (blog.)codecentric.de");
	}
}
