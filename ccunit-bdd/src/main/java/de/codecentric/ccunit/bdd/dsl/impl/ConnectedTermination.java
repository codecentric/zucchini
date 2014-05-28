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

package de.codecentric.ccunit.bdd.dsl.impl;

import de.codecentric.ccunit.bdd.ExecutionContext;
import de.codecentric.ccunit.bdd.ExecutionException;
import de.codecentric.ccunit.bdd.Executor;
import de.codecentric.ccunit.bdd.ExecutorHolder;
import de.codecentric.ccunit.bdd.dsl.Condition;
import de.codecentric.ccunit.bdd.dsl.Fact;
import de.codecentric.ccunit.bdd.dsl.Result;
import de.codecentric.ccunit.bdd.dsl.Termination;

import java.util.List;

public class ConnectedTermination implements Termination {
	private final List<Fact> facts;
	private final List<Condition> conditions;
	private final List<Result> results;

	ConnectedTermination(List<Fact> facts, List<Condition> conditions, List<Result> results) {
		this.facts = facts;
		this.conditions = conditions;
		this.results = results;
	}

	@Override
	public void end() {
		Executor executor = ExecutorHolder.getExecutor();
		if (executor == null) {
			throw new ExecutionException("No executor is defined. Use ExecutorHolder.setExecutor() to set one.");
		}
		executor.execute(new ExecutionContext(facts, conditions, results));
	}
}
