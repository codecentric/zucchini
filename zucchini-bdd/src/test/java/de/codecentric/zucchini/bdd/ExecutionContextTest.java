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

import org.junit.Test;
import org.mockito.ArgumentMatcher;

import static de.codecentric.zucchini.bdd.dsl.impl.TestContext.given;
import static de.codecentric.zucchini.bdd.dsl.impl.facts.Facts.noOpFact;
import static de.codecentric.zucchini.bdd.dsl.impl.results.Results.noOpResult;
import static de.codecentric.zucchini.bdd.dsl.impl.steps.Steps.noOpStep;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.*;

public class ExecutionContextTest {
	@Test
	public void testOneFactZeroStepsZeroResults() {
		Executor executorMock = mock(Executor.class);
		ExecutorHolder.setExecutor(executorMock);

		given(noOpFact()).end();

		verifyExecutionContext(executorMock, 1, 0, 0);
	}

	@Test
	public void testOneFactOneStepZeroResults() {
		Executor executorMock = mock(Executor.class);
		ExecutorHolder.setExecutor(executorMock);

		given(noOpFact()).when(noOpStep()).end();

		verifyExecutionContext(executorMock, 1, 1, 0);
	}

	@Test
	public void testOneFactTwoStepsZeroResults() {
		Executor executorMock = mock(Executor.class);
		ExecutorHolder.setExecutor(executorMock);

		given(noOpFact())
				.when(noOpStep())
				.andWhen(noOpStep())
				.end();

		verifyExecutionContext(executorMock, 1, 2, 0);
	}

	@Test
	public void testOneFactOneStepOneResult() {
		Executor executorMock = mock(Executor.class);
		ExecutorHolder.setExecutor(executorMock);

		given(noOpFact())
				.when(noOpStep())
				.then(noOpResult())
				.end();

		verifyExecutionContext(executorMock, 1, 1, 1);
	}

	@Test
	public void testOneFactOneStepThreeResults() {
		Executor executorMock = mock(Executor.class);
		ExecutorHolder.setExecutor(executorMock);

		given(noOpFact())
				.when(noOpStep())
				.then(noOpResult())
				.andThen(noOpResult())
				.andThen(noOpResult())
				.end();

		verifyExecutionContext(executorMock, 1, 1, 3);
	}

	private void verifyExecutionContext(Executor executorMock, final int expectedNumberOfFacts, final int expectedNumberOfSteps, final int expectedNumberOfResults) {
		verify(executorMock).execute(argThat(new ArgumentMatcher<ExecutionContext>() {
			@Override
			public boolean matches(Object argument) {
				if (!(argument instanceof ExecutionContext)) {
					return false;
				}
				ExecutionContext executionContext = (ExecutionContext) argument;
				if (executionContext.getFacts().size() != expectedNumberOfFacts) {
					return false;
				}
				if (executionContext.getSteps().size() != expectedNumberOfSteps) {
					return false;
				}
				if (executionContext.getResults().size() != expectedNumberOfResults) {
					return false;
				}
				return true;
			}
		}));
		verifyNoMoreInteractions(executorMock);
	}
}
