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
import de.codecentric.zucchini.bdd.resolver.StatementResolverHolder;
import de.codecentric.zucchini.bdd.resolver.VariableStatementResolver;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import static de.codecentric.zucchini.bdd.dsl.impl.ScenarioBuilder.given;

public class TextualStatementTest {
    @Before
    public void setUp() {
        ExecutorHolder.setExecutor(new SimpleExecutor());
        addFact("man");
        addFact("woman");
        addStep("love");
        addStep("commitment");
        addResult("baby");
        addResult("family");
    }

    /**
     * Reset the variable statement resolver so that statements registered within this test class are removed.
     *
     * Usually, this is not necessary.
     */
    @AfterClass
    public static void tearDown() {
        StatementResolverHolder.setStatementResolver(new VariableStatementResolver());
    }

    @Test
    public void testFamily() {
        given("man")
                .andGiven("woman")
                .when("love")
                .andWhen("commitment")
                .then("baby")
                .andThen("family")
                .end();
    }

    private void addFact(String fact) {
        StatementResolverHolder.getStatementResolver().addStatement(fact, (Fact) () -> System.out.println(fact), Fact.class);
    }

    private void addStep(String step) {
        StatementResolverHolder.getStatementResolver().addStatement(step, (Step) () -> System.out.println(step), Step.class);
    }


    private void addResult(String result) {
        StatementResolverHolder.getStatementResolver().addStatement(result, (Result) () -> System.out.println(result), Result.class);
    }

}
