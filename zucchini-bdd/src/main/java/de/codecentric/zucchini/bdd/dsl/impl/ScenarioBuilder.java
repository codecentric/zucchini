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

package de.codecentric.zucchini.bdd.dsl.impl;

import de.codecentric.zucchini.bdd.ExecutionContext;
import de.codecentric.zucchini.bdd.dsl.Fact;
import de.codecentric.zucchini.bdd.dsl.FirstFactContext;
import de.codecentric.zucchini.bdd.dsl.RepeatingFactContext;
import de.codecentric.zucchini.bdd.dsl.impl.facts.DelegatingFact;

public class ScenarioBuilder implements FirstFactContext {
	public static RepeatingFactContext given(Fact fact) {
		return new ConnectedRepeatingFactContext(new ExecutionContext().addFact(fact));
	}

	public static RepeatingFactContext given(String factName) {
		return given(new DelegatingFact(factName));
	}
}
