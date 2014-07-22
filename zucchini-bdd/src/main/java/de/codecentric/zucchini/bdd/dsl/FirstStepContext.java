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
 * Defines the part of the DSL that allows the definition of the first step.
 */
public interface FirstStepContext extends Termination {
	/**
	 * Defines that the given step will be gone next.
	 *
	 * @param step The next step.
	 * @return A context that allows to define additional steps.
	 */
	RepeatingStepContext when(Step step);

	/**
	 * Defines that the step referenced by the given name will be gone next.
	 *
	 * @param stepName The name of the referenced next step.
	 * @return A context that allows to define additional steps.
	 */
	RepeatingStepContext when(String stepName);
}
