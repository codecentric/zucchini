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

package de.codecentric.zucchini.bdd.dsl.impl;

import de.codecentric.zucchini.bdd.ExecutionContext;
import de.codecentric.zucchini.bdd.dsl.FirstStepContext;
import de.codecentric.zucchini.bdd.dsl.RepeatingStepContext;
import de.codecentric.zucchini.bdd.dsl.Step;
import de.codecentric.zucchini.bdd.dsl.impl.steps.DelegatingStep;

/**
 * Defines the part of the DSL that allows the definition of the first step and everything that a
 * {@link de.codecentric.zucchini.bdd.dsl.impl.ConnectedTermination} does.
 */
public class ConnectedFirstStepContext extends ConnectedTermination implements FirstStepContext {
	ConnectedFirstStepContext(ExecutionContext executionContext) {
		super(executionContext);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RepeatingStepContext when(Step step) {
		return new ConnectedRepeatingStepContext(getExecutionContext().addStep(step));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RepeatingStepContext when(String stepName) {
		return when(new DelegatingStep(stepName));
	}
}
