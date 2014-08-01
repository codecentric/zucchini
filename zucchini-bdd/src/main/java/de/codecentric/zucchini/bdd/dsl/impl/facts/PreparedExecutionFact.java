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

package de.codecentric.zucchini.bdd.dsl.impl.facts;

import de.codecentric.zucchini.bdd.ExecutionContext;
import de.codecentric.zucchini.bdd.ExecutorHolder;
import de.codecentric.zucchini.bdd.dsl.*;

import java.util.Map;

/**
 * {@inheritDoc}
 */
public class PreparedExecutionFact implements ExecutionFact, VariablesAware {
	private final ExecutionContext executionContext;

	public PreparedExecutionFact(ExecutionContext executionContext) {
		this.executionContext = executionContext;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void establish() {
		ExecutorHolder.getExecutor().execute(executionContext);
	}

    @Override
    public void setVariables(Map<String, String> variables) {
        for (Fact fact : executionContext.getFacts()) {
            injectVariables(variables, fact);
        }
        for (Step step : executionContext.getSteps()) {
            injectVariables(variables, step);
        }
        for (Result result : executionContext.getResults()) {
            injectVariables(variables, result);
        }
    }
    
    private void injectVariables(Map<String, String> variables, Statement statement) {
        if (statement instanceof VariablesAware) {
            ((VariablesAware)statement).setVariables(variables);
        }
    }
}
