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

import de.codecentric.zucchini.bdd.dsl.Step;
import de.codecentric.zucchini.bdd.resolver.StatementResolverHolder;
import de.codecentric.zucchini.web.steps.WebStep;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class WebStepRule implements TestRule {
	private final String stepName;
	private final WebStep webStep;

	public WebStepRule(String stepName, WebStep webStep) {
		this.stepName = stepName;
		this.webStep = webStep;
	}

	@Override
	public Statement apply(final Statement base, Description description) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				StatementResolverHolder.getStatementResolver().addStatement(stepName, webStep,
						Step.class);
				base.evaluate();
			}
		};
	}
}
