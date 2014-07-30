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

/**
 * This JUnit {@link org.junit.rules.TestRule rule} provides a mechanism for registering a web fact with a specific
 * name.
 */
public class WebFactRule implements TestRule {
	private final String factName;
	private final Fact fact;

	/**
	 * Initializes a web fact rule that registers the given {@link de.codecentric.zucchini.web.facts.WebFact web fact}
	 * with the specified name.
	 *
	 * @param factName The name used to reference the fact afterwards.
	 * @param webFact  The {@link de.codecentric.zucchini.web.facts.WebFact web fact}.
	 */
	public WebFactRule(String factName, WebFact webFact) {
		this.factName = factName;
		this.fact = webFact;
	}

	/**
	 * Initializes a web fact rule that registers the given
	 * {@link de.codecentric.zucchini.bdd.dsl.ExecutionFact execution fact} with the specified name.
	 *
	 * @param factName      The name used to reference the fact afterwards.
	 * @param executionFact The {@link de.codecentric.zucchini.bdd.dsl.ExecutionFact execution fact}.
	 */
	public WebFactRule(String factName, ExecutionFact executionFact) {
		this.factName = factName;
		this.fact = executionFact;
	}

	/**
	 * {@inheritDoc}
	 */
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
