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

import de.codecentric.zucchini.bdd.dsl.Result;

/**
 * A non-operational result is a dummy result that does nothing.
 */
public class NonOperationalResult implements Result {
	/**
	 * This method does nothing.
	 */
	@Override
	public void expect() {
		// Intentionally left blank.
	}
}
