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

package de.codecentric.zucchini.bdd.resolver;

import de.codecentric.zucchini.bdd.dsl.Fact;
import de.codecentric.zucchini.bdd.dsl.Result;
import de.codecentric.zucchini.bdd.dsl.Statement;
import de.codecentric.zucchini.bdd.dsl.Step;

import java.util.HashMap;
import java.util.Map;

/**
 * This is the built-in statement resolver.
 */
public class SimpleStatementResolver implements StatementResolver {
	private final Map<Class<? extends Statement>, Map<String, Statement>> statements = new HashMap<Class<? extends Statement>, Map<String, Statement>>();

	/**
	 * Initializes the statement resolver.
	 */
	public SimpleStatementResolver() {
		statements.put(Fact.class, new HashMap<String, Statement>());
		statements.put(Step.class, new HashMap<String, Statement>());
		statements.put(Result.class, new HashMap<String, Statement>());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addStatement(String statementName, Statement statement, Class<? extends Statement> type) {
		statements.get(type).put(statementName, statement);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T extends Statement> T resolveStatement(String statementName, Class<T> type) {
		if (statementName == null) {
			throw new NullPointerException("The fact name must not be null.");
		}
		Map<String, Statement> statementMap = statements.get(type);
		if (!statementMap.containsKey(statementName)) {
			throw new UnknownStatementException(String.format("Could not find a statement with the name %s.", statementName));
		}
		return type.cast(statementMap.get(statementName));
	}
}
