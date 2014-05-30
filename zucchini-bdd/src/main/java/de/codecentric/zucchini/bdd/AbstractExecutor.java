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
import de.codecentric.zucchini.bdd.dsl.Statement;
import de.codecentric.zucchini.bdd.dsl.Step;

import java.util.List;

public abstract class AbstractExecutor implements Executor {
	private LogStrategy logStrategy = new DefaultLogStrategy();

	@Override
	public void execute(ExecutionContext executionContext) throws ExecutionException {
		initialize();
		failOnInvalidContext(executionContext);
		establishFacts(executionContext.getFacts());
		goSteps(executionContext.getSteps());
		expectResults(executionContext.getResults());
		shutdown();
	}

	private void establishFacts(List<Fact> facts) {
		for (Fact fact : facts) {
			logStrategy.writeLog(fact);
			prepareStatement(fact);
			fact.establish();
		}
	}

	private void goSteps(List<Step> steps) {
		for (Step step : steps) {
			logStrategy.writeLog(step);
			prepareStatement(step);
			step.go();
		}
	}

	private void expectResults(List<Result> results) {
		for (Result result : results) {
			logStrategy.writeLog(result);
			prepareStatement(result);
			result.expect();
		}
	}

	public final void setLogStrategy(LogStrategy logStrategy) {
		if (logStrategy == null) {
			throw new NullPointerException("The LogStrategy must not be null.");
		}
		this.logStrategy = logStrategy;
	}

	protected abstract void initialize();

	protected abstract void shutdown();

	protected abstract void failOnInvalidContext(ExecutionContext executionContext);

	protected void prepareStatement(Statement statement) {
		// Intentionally left blank.
	}
}
