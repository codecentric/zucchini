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

/**
 * The statement resolver holder is used to define a {@link de.codecentric.zucchini.bdd.resolver.StatementResolver}
 * which is used to resolve statements that are referenced by their name.
 * <p/>
 * If no statement resolver is registered, the {@link de.codecentric.zucchini.bdd.resolver.SimpleStatementResolver} will
 * be used which should suit most purposes.
 */
public class StatementResolverHolder {
	private static final ThreadLocal<StatementResolver> factResolver = new ThreadLocal<StatementResolver>();

	static {
		setFactResolver(new SimpleStatementResolver());
	}

	/**
	 * Returns the statement resolver of the current thread.
	 *
	 * @return The statement resolver of the current thread.
	 */
	public static StatementResolver getStatementResolver() {
		return factResolver.get();
	}

	/**
	 * Sets the statement resolver for the current thread.
	 *
	 * @param statementResolver The statement resolver for the current thread.
	 */
	@SuppressWarnings("WeakerAccess")
	public static void setFactResolver(StatementResolver statementResolver) {
		if (statementResolver == null) {
			throw new NullPointerException("The statement resolver must not be null.");
		}
		StatementResolverHolder.factResolver.set(statementResolver);
	}
}
