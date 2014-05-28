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

package de.codecentric.ccunit.bdd;

import de.codecentric.ccunit.bdd.annotations.Given;
import de.codecentric.ccunit.bdd.annotations.Then;
import de.codecentric.ccunit.bdd.annotations.When;
import de.codecentric.ccunit.bdd.dsl.Condition;
import de.codecentric.ccunit.bdd.dsl.Fact;
import de.codecentric.ccunit.bdd.dsl.Result;
import de.codecentric.ccunit.bdd.dsl.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultLogStrategy implements LogStrategy {
	private static final Logger logger = LoggerFactory.getLogger(DefaultLogStrategy.class);

	@Override
	public void writeLog(Statement statement) {
		if (statement instanceof Fact) {
			writeLog((Fact) statement);
		} else if (statement instanceof Condition) {
			writeLog((Condition) statement);
		} else if (statement instanceof Result) {
			writeLog((Result) statement);
		}
	}

	protected void writeLog(Fact fact) {
		Class<?> factClass = fact.getClass();
		Given givenAnnotation = factClass.getAnnotation(Given.class);
		logger.info("Given: {}", givenAnnotation == null ? factClass.getName() : givenAnnotation.value());
	}

	protected void writeLog(Condition condition) {
		Class<?> conditionClass = condition.getClass();
		When whenAnnotation = conditionClass.getAnnotation(When.class);
		logger.info("When: {}", whenAnnotation == null ? conditionClass.getName() : whenAnnotation.value());
	}

	protected void writeLog(Result result) {
		Class<?> resultClass = result.getClass();
		Then thenAnnotation = resultClass.getAnnotation(Then.class);
		logger.info("Then: {}", thenAnnotation == null ? resultClass.getName() : thenAnnotation.value());
	}
}
