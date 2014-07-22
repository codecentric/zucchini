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

package de.codecentric.zucchini.bdd.dsl;

/**
 * Defines the part of the DSL that allows the definition of additional facts or the first step.
 */
public interface RepeatingFactContext extends FirstStepContext {
	/**
	 * Defines that the given fact will be established.
	 *
	 * @param fact The fact that will be established.
	 * @return A context that allows to define additional facts that will be established.
	 */
	RepeatingFactContext andGiven(Fact fact);

	/**
	 * Defines that the fact referenced by the given name will be established.
	 *
	 * @param factName The name of the referenced fact that will be established.
	 * @return A context that allows to define additional facts that will be established.
	 */
	RepeatingFactContext andGiven(String factName);
}
