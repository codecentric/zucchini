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

package de.codecentric.zucchini.bdd;

/**
 * The executor is responsible for the execution of facts, steps, and results.
 */
public interface Executor {
	/**
	 * This method runs the execution of facts, steps, and results that are defined in the
	 * {@link de.codecentric.zucchini.bdd.ExecutionContext}
	 *
	 * @param executionContext The execution context defining facts, steps, and results.
	 * @throws ExecutionException Thrown if the execution failed.
	 */
	void execute(ExecutionContext executionContext) throws ExecutionException;
}
