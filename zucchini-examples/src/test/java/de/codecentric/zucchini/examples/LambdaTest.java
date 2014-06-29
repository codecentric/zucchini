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

package de.codecentric.zucchini.examples;

import de.codecentric.zucchini.bdd.ExecutorHolder;
import de.codecentric.zucchini.bdd.SimpleExecutor;
import de.codecentric.zucchini.bdd.dsl.Fact;
import de.codecentric.zucchini.bdd.dsl.Result;
import de.codecentric.zucchini.bdd.dsl.Step;
import org.junit.Before;
import org.junit.Test;

import static de.codecentric.zucchini.bdd.dsl.impl.ScenarioBuilder.given;

public class LambdaTest {
	@Before
	public void setUp() {
		ExecutorHolder.setExecutor(new SimpleExecutor());
	}

	@Test
	public void testHumanityWithLambda() {
		given(() -> System.out.println("Man and woman"))
				.when(() -> System.out.println("love"))
				.then(() -> System.out.println("baby"))
				.end();
	}

	@SuppressWarnings("Convert2Lambda")
	@Test
	public void testHumanityWithoutLambda() {
		given(new Fact() {
			@Override
			public void establish() {
				System.out.println("Man and woman");
			}
		}).when(new Step() {
			@Override
			public void go() {
				System.out.println("love");
			}
		}).then(new Result() {
			@Override
			public void expect() {
				System.out.println("baby");
			}
		}).end();
	}
}
