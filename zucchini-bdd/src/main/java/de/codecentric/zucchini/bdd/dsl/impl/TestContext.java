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
import de.codecentric.zucchini.bdd.dsl.*;
import de.codecentric.zucchini.bdd.dsl.impl.ConnectedRepeatingFactContext;
import de.codecentric.zucchini.bdd.resolver.StatementResolverHolder;

import java.util.ArrayList;

import static de.codecentric.zucchini.bdd.dsl.impl.util.ArrayConverter.createMutableList;

public class TestContext implements FirstCausationContext {
	public static RepeatingFactContext given(Fact fact) {
		return new ConnectedRepeatingFactContext(new ExecutionContext(createMutableList(fact), new ArrayList<Step>(), new ArrayList<Result>()));
	}

	public static RepeatingFactContext given(String fact) {
		return given(StatementResolverHolder.getStatementResolver().resolveStatement(fact, Fact.class));
	}
}
