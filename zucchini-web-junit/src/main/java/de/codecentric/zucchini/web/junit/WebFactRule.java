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

import de.codecentric.zucchini.bdd.dsl.ExecutionFact;
import de.codecentric.zucchini.bdd.dsl.Fact;
import de.codecentric.zucchini.bdd.resolver.StatementResolverHolder;
import de.codecentric.zucchini.web.facts.WebFact;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class WebFactRule implements TestRule {
	private final String factName;
	private Fact fact;

	public WebFactRule(String factName, WebFact webFact) {
		this.factName = factName;
		this.fact = webFact;
	}

	public WebFactRule(String factName, ExecutionFact executionFact) {
		this.factName = factName;
		this.fact = executionFact;
	}

	@Override
	public Statement apply(final Statement base, Description description) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				StatementResolverHolder.getStatementResolver().addStatement(factName, fact, Fact.class);
				base.evaluate();
			}
		};
	}
}
