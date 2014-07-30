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

package de.codecentric.zucchini.web.steps;

import de.codecentric.zucchini.bdd.ExecutionException;
import de.codecentric.zucchini.bdd.vars.Variable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * A wait step waits for while, hence it gives a page time to load or run JavaScript.
 */
public class WaitStep extends AbstractWebStep {
	private static final Logger logger = LoggerFactory.getLogger(WaitStep.class);

	private static final long DEFAULT_SLEEP_TIME = 10000;

	private long sleepTime;
    private Variable<Long> sleepTimeVariable;

    /**
	 * Initializes a wait step with a sleep time of 10 seconds.
	 */
	public WaitStep() {
		this.sleepTime = DEFAULT_SLEEP_TIME;
	}

    /**
     * Initializes a wait time.
     *
     * @param sleepTime The wait timeout in milliseconds.
     */
    public WaitStep(long sleepTime) {
        this.sleepTime = sleepTime;
    }

    /**
     * Initializes a wait time.
     *
     * @param sleepTimeVariable The wait timeout in milliseconds.
     */
    public WaitStep(Variable<Long> sleepTimeVariable) {
        this.sleepTimeVariable = sleepTimeVariable;
    }

    /**
     *
     * @param sleepTime
     * @return
     */
    /**
     * This method is a convenience method that integrates well into the DSL.
     *
     * For example:
     * <code>
     * given(...)
     * .when(wait().withTimeout(5000))
     * .then(...)
     * .end();
     * </code>
     *
     * @param sleepTime The wait timeout in milliseconds.
     * @return A wait step with the specified sleep time.
     */
    public WaitStep withSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
        return this;
    }

    /**
     *
     * @param sleepTime
     * @return
     */
    /**
     * This method is a convenience method that integrates well into the DSL.
     *
     * For example:
     * <code>
     * given(...)
     * .when(wait().withTimeout(longVar("sleepTime")))
     * .then(...)
     * .end();
     * </code>
     *
     * @param sleepTimeVariable A variable that contains the wait timeout in milliseconds.
     * @return A wait step with the specified sleep time.
     */
    public WaitStep withSleepTime(Variable<Long> sleepTimeVariable) {
        this.sleepTimeVariable = sleepTimeVariable;
        return this;
    }

	/**
	 * Waits for the specified sleep time.
	 */
	@Override
	public void go() {
		logger.info("Waiting {} seconds...", sleepTime);
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			throw new ExecutionException("Thread could not sleep.", e);
		}
	}

    @Override
    public void setVariables(Map<String, String> variables) {
        if (sleepTimeVariable != null) {
            sleepTime = sleepTimeVariable.convert(variables.get(sleepTimeVariable.getName()));
        }
    }
}
