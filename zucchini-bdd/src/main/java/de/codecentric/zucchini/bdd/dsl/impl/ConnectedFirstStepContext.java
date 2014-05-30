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

import de.codecentric.zucchini.bdd.dsl.Fact;
import de.codecentric.zucchini.bdd.dsl.FirstStepContext;
import de.codecentric.zucchini.bdd.dsl.RepeatingStepContext;
import de.codecentric.zucchini.bdd.dsl.Step;
import de.codecentric.zucchini.bdd.resolver.StatementResolverHolder;

import java.util.List;

public class ConnectedFirstStepContext implements FirstStepContext {
	private final List<Fact> facts;

	ConnectedFirstStepContext(List<Fact> facts) {
		this.facts = facts;
	}

	@Override
	public RepeatingStepContext when(Step step) {
		return new ConnectedRepeatingStepContext(facts, step);
	}

	@Override
	public RepeatingStepContext when(String stepName) {
		return when(StatementResolverHolder.getStatementResolver().resolveStatement(stepName, Step.class));
	}

	List<Fact> getFacts() {
		return facts;
	}
}
