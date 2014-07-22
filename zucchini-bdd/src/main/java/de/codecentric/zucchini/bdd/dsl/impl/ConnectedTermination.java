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
import de.codecentric.zucchini.bdd.ExecutionException;
import de.codecentric.zucchini.bdd.Executor;
import de.codecentric.zucchini.bdd.ExecutorHolder;
import de.codecentric.zucchini.bdd.dsl.ExecutionFact;
import de.codecentric.zucchini.bdd.dsl.Fact;
import de.codecentric.zucchini.bdd.dsl.Termination;
import de.codecentric.zucchini.bdd.dsl.impl.facts.PreparedExecutionFact;
import de.codecentric.zucchini.bdd.resolver.StatementResolverHolder;

/**
 * {@inheritDoc}
 */
public class ConnectedTermination implements Termination {
	private final ExecutionContext executionContext;

	ConnectedTermination(ExecutionContext executionContext) {
		this.executionContext = executionContext;
	}

	/**
	 * Returns the execution context.
	 *
	 * @return The execution context.
	 */
	final ExecutionContext getExecutionContext() {
		return executionContext;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void end() {
		getExecutor().execute(getExecutionContext());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final ExecutionFact asFact() {
		return new PreparedExecutionFact(getExecutionContext());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void registerAsFact(String factName) {
		StatementResolverHolder.getStatementResolver().addStatement(factName, asFact(), Fact.class);
	}

	private Executor getExecutor() {
		Executor executor = ExecutorHolder.getExecutor();
		if (executor == null) {
			throw new ExecutionException("No executor is defined. Use ExecutorHolder.setExecutor() to set one.");
		}
		return executor;
	}
}
