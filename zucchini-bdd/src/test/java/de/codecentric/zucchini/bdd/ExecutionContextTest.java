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

import static de.codecentric.zucchini.bdd.dsl.TestContext.given;
import static de.codecentric.zucchini.bdd.dsl.impl.facts.Facts.noOpFact;
import static de.codecentric.zucchini.bdd.dsl.impl.results.Results.noOpResult;
import static de.codecentric.zucchini.bdd.dsl.impl.steps.Steps.noOpStep;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.*;

public class ExecutionContextTest {

	@Test
	public void testOpenGoogle() {
		Executor executorMock = mock(Executor.class);

		ExecutorHolder.setExecutor(executorMock);

		given(noOpFact())
				.when(noOpStep())
				.then(noOpResult())
				.andThen(noOpResult())
				.andThen(noOpResult())
				.end();

		verify(executorMock).execute(argThat(new ArgumentMatcher<ExecutionContext>() {
			@Override
			public boolean matches(Object argument) {
				if (!(argument instanceof ExecutionContext)) {
					return false;
				}
				ExecutionContext executionContext = (ExecutionContext) argument;
				if (executionContext.getFacts().size() != 1) {
					return false;
				}
				if (executionContext.getSteps().size() != 1) {
					return false;
				}
				if (executionContext.getResults().size() != 3) {
					return false;
				}
				return true;
			}
		}));
		verifyNoMoreInteractions(executorMock);
	}

}
