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

package de.codecentric.zucchini.bdd.dsl.impl.facts;

import de.codecentric.zucchini.bdd.dsl.DelegatingStatement;
import de.codecentric.zucchini.bdd.dsl.Fact;
import de.codecentric.zucchini.bdd.resolver.StatementResolverHolder;

/**
 * A delegating fact is a fact that delegates the call of {@code establish()} to another fact which is referenced by
 * name.
 * <p/>
 * The fact which is referenced must be registered using a
 * {@link de.codecentric.zucchini.bdd.resolver.StatementResolver}.
 */
public class DelegatingFact implements Fact, DelegatingStatement<Fact> {
	private final String factName;

	private Fact fact;

	/**
	 * Initializes a delegating fact which references an actual fact by its name.
	 *
	 * @param factName The name of the actual fact.
	 */
	public DelegatingFact(String factName) {
		this.factName = factName;
	}

	/**
	 * Delegates the call to the fact which has been referenced by name at construction time.
	 */
	@Override
	public void establish() {
		getStatement().establish();
	}

	/**
	 * Lazily loads the fact which has been referenced by name at construction time.
	 *
	 * @return The actual fact.
	 */
	@Override
	public Fact getStatement() {
		if (fact == null) {
			fact = StatementResolverHolder.getStatementResolver().resolveStatement(factName, Fact.class);
		}
		return fact;
	}
}
