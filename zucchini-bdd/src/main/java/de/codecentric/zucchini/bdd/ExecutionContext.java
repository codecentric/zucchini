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

import de.codecentric.zucchini.bdd.dsl.Fact;
import de.codecentric.zucchini.bdd.dsl.Result;
import de.codecentric.zucchini.bdd.dsl.Step;

import java.util.ArrayList;
import java.util.List;

public class ExecutionContext {
	private final List<Fact> facts = new ArrayList<Fact>();
	private final List<Step> steps = new ArrayList<Step>();
	private final List<Result> results = new ArrayList<Result>();

	public List<Fact> getFacts() {
		return facts;
	}

	public ExecutionContext addFact(Fact fact) {
		facts.add(fact);
		return this;
	}

	public List<Step> getSteps() {
		return steps;
	}

	public ExecutionContext addStep(Step step) {
		steps.add(step);
		return this;
	}

	public List<Result> getResults() {
		return results;
	}

	public ExecutionContext addResult(Result result) {
		results.add(result);
		return this;
	}
}
