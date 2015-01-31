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

package de.codecentric.zucchini.bdd.dsl.impl.steps;

import de.codecentric.zucchini.bdd.dsl.DelegatingStatement;
import de.codecentric.zucchini.bdd.dsl.Step;
import de.codecentric.zucchini.bdd.resolver.StatementResolverHolder;

/**
 * A delegating step is a step that delegates the call of {@code go()} to another step which is referenced  by
 * name.
 *
 * The step which is referenced must be registered using a
 * {@link de.codecentric.zucchini.bdd.resolver.StatementResolver}.
 */
public class DelegatingStep implements Step, DelegatingStatement<Step> {
	private final String stepName;

	/**
	 * Initializes a delegating step which references an actual step by its name.
	 *
	 * @param stepName The name of the actual step.
	 */
	public DelegatingStep(String stepName) {
		this.stepName = stepName;
	}

	/**
	 * Delegates the call to the step which has been referenced by name at construction time.
	 */
	@Override
	public void go() {
		getStatement().go();
	}

	/**
	 * Eagerly loads the step which has been referenced by name at construction time.
	 *
	 * @return The actual step.
	 */
	public Step getStatement() {
		return StatementResolverHolder.getStatementResolver().resolveStatement(stepName, Step.class);
	}
}
