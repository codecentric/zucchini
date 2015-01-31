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

package de.codecentric.zucchini.bdd.dsl.impl.results;

import de.codecentric.zucchini.bdd.dsl.DelegatingStatement;
import de.codecentric.zucchini.bdd.dsl.Result;
import de.codecentric.zucchini.bdd.resolver.StatementResolverHolder;

/**
 * A delegating result is a result that delegates the call of {@code expect()} to another result which is referenced  by
 * name.
 *
 * The result which is referenced must be registered using a
 * {@link de.codecentric.zucchini.bdd.resolver.StatementResolver}.
 */
public class DelegatingResult implements Result, DelegatingStatement<Result> {
	private final String resultName;

	/**
	 * Initializes a delegating result which references an actual result by its name.
	 *
	 * @param resultName The name of the actual result.
	 */
	public DelegatingResult(String resultName) {
		this.resultName = resultName;
	}

	/**
	 * Delegates the call to the result which has been referenced by name at construction time.
	 */
	@Override
	public void expect() {
		getStatement().expect();
	}

	/**
	 * Eagerly loads the result which has been referenced by name at construction time.
	 *
	 * @return The actual result.
	 */
	@Override
	public Result getStatement() {
		return StatementResolverHolder.getStatementResolver().resolveStatement(resultName, Result.class);
	}
}
