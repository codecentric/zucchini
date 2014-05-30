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

package de.codecentric.zucchini.bdd.dsl;

import de.codecentric.zucchini.bdd.dsl.impl.steps.NonOperationalStep;
import de.codecentric.zucchini.bdd.dsl.impl.facts.NonOperationalFact;
import de.codecentric.zucchini.bdd.dsl.impl.results.NonOperationalResult;
import org.junit.Test;

import static de.codecentric.zucchini.bdd.dsl.TestContext.given;

public class DslTest {

	@Test
	public void testOneGivenOneWhenOneThen() {
		given(new NonOperationalFact())
				.when(new NonOperationalStep())
				.then(new NonOperationalResult())
				.end();
	}

	@Test
	public void testTwoGivenOneWhenOneThen() {
		given(new NonOperationalFact())
				.andGiven(new NonOperationalFact())
				.when(new NonOperationalStep())
				.then(new NonOperationalResult())
				.end();
	}

	@Test
	public void testThreeGivenOneWhenOneThen() {
		given(new NonOperationalFact())
				.andGiven(new NonOperationalFact())
				.andGiven(new NonOperationalFact())
				.when(new NonOperationalStep())
				.then(new NonOperationalResult())
				.end();
	}

	@Test
	public void testOneGivenTwoWhenOneThen() {
		given(new NonOperationalFact())
				.when(new NonOperationalStep())
				.andWhen(new NonOperationalStep())
				.then(new NonOperationalResult())
				.end();
	}

	@Test
	public void testTwoGivenTwoWhenOneThen() {
		given(new NonOperationalFact())
				.andGiven(new NonOperationalFact())
				.when(new NonOperationalStep())
				.andWhen(new NonOperationalStep())
				.then(new NonOperationalResult())
				.end();
	}

	@Test
	public void testOneGivenOneWhenTwoThen() {
		given(new NonOperationalFact())
				.when(new NonOperationalStep())
				.then(new NonOperationalResult())
				.andThen(new NonOperationalResult())
				.end();
	}

	@Test
	public void testThreeGivenThreeWhenTwoThen() {
		given(new NonOperationalFact())
				.andGiven(new NonOperationalFact())
				.andGiven(new NonOperationalFact())
				.when(new NonOperationalStep())
				.andWhen(new NonOperationalStep())
				.andWhen(new NonOperationalStep())
				.then(new NonOperationalResult())
				.end();
	}
}
