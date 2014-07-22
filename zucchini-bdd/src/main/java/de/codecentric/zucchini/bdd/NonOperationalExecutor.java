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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is a dummy {@link de.codecentric.zucchini.bdd.Executor} which does nothing but writing a warning to the log.
 *
 * You should manually set an executor using {@code ExecutorHolder.setExecutor()}.
 */
public class NonOperationalExecutor implements Executor {
	private static final Logger logger = LoggerFactory.getLogger(NonOperationalExecutor.class);

	/**
	 * This method only prints a warning.
	 *
	 * @param executionContext The execution context defining facts, steps, and results.
	 */
	@Override
	public void execute(ExecutionContext executionContext) {
		logger.warn("No executor has been defined. See ExecutorHolder.setExecutor() for more information.");
	}
}
