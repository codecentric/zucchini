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
 * Defines the part of the DSL that allows the definition of the first result.
 */
public interface FirstResultContext {
	/**
	 * Defines that the given result will be expected.
	 *
	 * @param result The expected result.
	 * @return A context that allows to define additional expected results.
	 */
	RepeatingResultContext then(Result result);

	/**
	 * Defines that the result referenced by the given name will be expected.
	 *
	 * @param resultName The name of the referenced expected result.
	 * @return A context that allows to define additional expected results.
	 */
	RepeatingResultContext then(String resultName);
}
