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

import de.codecentric.zucchini.bdd.dsl.Statement;

/**
 * A statement resolver can be used to register statements globally so that they can be referenced by name.
 */
public interface StatementResolver {
	/**
	 * Adds a statement to the registry.
	 * <p/>
	 * Registered statements can be resolved with {@code resolveStatement()}.
	 *
	 * @param statementName The name used to reference the statement afterwards.
	 * @param statement     The statement that shall be registered.
	 * @param type          The type of the statement, i.e. {@link de.codecentric.zucchini.bdd.dsl.Fact},
	 *                      {@link de.codecentric.zucchini.bdd.dsl.Step},
	 *                      {@link de.codecentric.zucchini.bdd.dsl.Result}, or their subclasses.
	 */
	void addStatement(String statementName, Statement statement, Class<? extends Statement> type);

	/**
	 * Resolves a statement using its name and the type of the statement.
	 *
	 * @param statementName The name used to reference the statement.
	 * @param type          The type of the statement, i.e. {@link de.codecentric.zucchini.bdd.dsl.Fact},
	 *                      {@link de.codecentric.zucchini.bdd.dsl.Step},
	 *                      {@link de.codecentric.zucchini.bdd.dsl.Result}, or their subclasses.
	 * @param <T>           The result type equates to the parameter {@code type}.
	 * @return The resolved statement.
	 */
	<T extends Statement> T resolveStatement(String statementName, Class<T> type);
}
