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


import de.codecentric.zucchini.bdd.dsl.*;

import java.util.List;

/**
 * This abstract executor executes facts, steps, and results in this order and provides initialization, shutdown,
 * {@link de.codecentric.zucchini.bdd.ExecutionContext} validation, and statement pre-processing methods. Most
 * {@link de.codecentric.zucchini.bdd.Executor} implementations will inherit from this class.
 */
public abstract class AbstractExecutor implements Executor {
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(ExecutionContext executionContext) {
        initialize();
        try {
            failOnInvalidContext(executionContext);
            establishFacts(executionContext.getFacts());
            goSteps(executionContext.getSteps());
            expectResults(executionContext.getResults());
        } finally {
            shutdown();
        }
    }

    private void establishFacts(List<Fact> facts) {
        for (Fact fact : facts) {
            prepareStatement(fact);
            fact.establish();
        }
    }

    private void goSteps(List<Step> steps) {
        for (Step step : steps) {
            prepareStatement(step);
            step.go();
        }
    }

    private void expectResults(List<Result> results) {
        for (Result result : results) {
            prepareStatement(result);
            result.expect();
        }
    }

    /**
     * This method is meant to be overridden for initialization purposes.
     *
     * If this method is not overridden, no initialization will occur. This method is called first, right before the
     * {@link de.codecentric.zucchini.bdd.ExecutionContext} is validated using {@code failOnInvalidContext()}.
     */
    protected void initialize() {
        // Intentionally left blank.
    }

    /**
     * This method is meant to be overridden for shutdown purposes.
     *
     * If this method is not overridden, no shutdown will occur. This method is called last, right after the execution
     * is completed. This method is called no matter whether the execution failed or not. An exception thrown during the
     * execution process does not affect the execution of this method.
     */
    protected void shutdown() {
        // Intentionally left blank.
    }

    /**
     * This method is meant ot be overridden for context validation purposes.
     *
     * If this method is not overridden, no context validation will occur. This method is called right after
     * {@code initialize()}. Implementations can throw any unchecked exception but it is recommended to throw an instance
     * of {@link de.codecentric.zucchini.bdd.ExecutionException}.
     *
     * @param executionContext The context that shall be validated.
     */
    @SuppressWarnings("UnusedParameters")
    protected void failOnInvalidContext(ExecutionContext executionContext) {
        // Intentionally left blank.
    }

    /**
     * This method is meant to be overridden for statement pre-processing purposes.
     *
     * If this method is not overridden, no pre-processing of statements will occur. This method is called right before
     * a statement is executed.
     *
     * @param statement The statement that shall be pre-processed.
     */
    @SuppressWarnings("UnusedParameters")
    protected void prepareStatement(Statement statement) {
        // Intentionally left blank.
    }
}
