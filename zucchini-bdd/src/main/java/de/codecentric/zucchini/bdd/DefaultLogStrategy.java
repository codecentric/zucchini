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

import de.codecentric.zucchini.bdd.annotations.Given;
import de.codecentric.zucchini.bdd.annotations.Then;
import de.codecentric.zucchini.bdd.annotations.When;
import de.codecentric.zucchini.bdd.dsl.Step;
import de.codecentric.zucchini.bdd.dsl.Fact;
import de.codecentric.zucchini.bdd.dsl.Result;
import de.codecentric.zucchini.bdd.dsl.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultLogStrategy implements LogStrategy {
	private static final Logger logger = LoggerFactory.getLogger(DefaultLogStrategy.class);

	@Override
	public void writeLog(Statement statement) {
		if (statement instanceof Fact) {
			writeLog((Fact) statement);
		} else if (statement instanceof Step) {
			writeLog((Step) statement);
		} else if (statement instanceof Result) {
			writeLog((Result) statement);
		}
	}

	protected void writeLog(Fact fact) {
		Class<?> factClass = fact.getClass();
		Given givenAnnotation = factClass.getAnnotation(Given.class);
		logger.info("Given: {}", givenAnnotation == null ? factClass.getName() : givenAnnotation.value());
	}

	protected void writeLog(Step step) {
		Class<?> stepClass = step.getClass();
		When whenAnnotation = stepClass.getAnnotation(When.class);
		logger.info("When: {}", whenAnnotation == null ? stepClass.getName() : whenAnnotation.value());
	}

	protected void writeLog(Result result) {
		Class<?> resultClass = result.getClass();
		Then thenAnnotation = resultClass.getAnnotation(Then.class);
		logger.info("Then: {}", thenAnnotation == null ? resultClass.getName() : thenAnnotation.value());
	}
}
