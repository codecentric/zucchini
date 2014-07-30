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

package de.codecentric.zucchini.bdd.resolver;

import de.codecentric.zucchini.bdd.dsl.Fact;
import de.codecentric.zucchini.bdd.dsl.Result;
import de.codecentric.zucchini.bdd.dsl.Step;
import de.codecentric.zucchini.bdd.dsl.VariablesAware;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static de.codecentric.zucchini.bdd.dsl.impl.facts.Facts.noContext;
import static de.codecentric.zucchini.bdd.dsl.impl.results.Results.noResult;
import static de.codecentric.zucchini.bdd.dsl.impl.steps.Steps.noOperation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class VariableStatementResolverTest {
    private VariableStatementResolver variableStatementResolver;

    @Before
    public void setUp() {
        variableStatementResolver = new VariableStatementResolver();
    }

    @Test
    public void testResolveStatementWithLiteralsOnly() {
        Fact fact = noContext();
        variableStatementResolver.addStatement("a b c", fact, Fact.class);
        assertEquals(fact, variableStatementResolver.resolveStatement("a b c", Fact.class));
    }

    @Test
    public void testResolveStatementWithVariable() {
        Fact fact = noContext();
        variableStatementResolver.addStatement("a ${b} c", fact, Fact.class);
        assertEquals(fact, variableStatementResolver.resolveStatement("a b c", Fact.class));
    }

    @Test
    public void testResolveStatementWithEscapedLiteral() {
        Fact fact = noContext();
        variableStatementResolver.addStatement("a ${b} c", fact, Fact.class);
        assertEquals(fact, variableStatementResolver.resolveStatement("a \"b c\" c", Fact.class));
    }

    @Test
    public void testResolveStatementWithVariableWithMultipleKnownFacts() {
        Fact fact1 = noContext();
        Fact fact2 = noContext();
        Fact fact3 = noContext();
        variableStatementResolver.addStatement("${a} b c", fact1, Fact.class);
        variableStatementResolver.addStatement("a ${b} c", fact2, Fact.class);
        variableStatementResolver.addStatement("a b ${c}", fact3, Fact.class);
        assertTrue(fact1 == variableStatementResolver.resolveStatement("x b c", Fact.class));
        assertTrue(fact2 == variableStatementResolver.resolveStatement("a x c", Fact.class));
        assertTrue(fact3 == variableStatementResolver.resolveStatement("a b x", Fact.class));
    }

    @Test
    public void testResolveStatementWithVariableWithMultipleKnownSteps() {
        Step step1 = noOperation();
        Step step2 = noOperation();
        Step step3 = noOperation();
        variableStatementResolver.addStatement("${a} b c", step1, Step.class);
        variableStatementResolver.addStatement("a ${b} c", step2, Step.class);
        variableStatementResolver.addStatement("a b ${c}", step3, Step.class);
        assertTrue(step1 == variableStatementResolver.resolveStatement("x b c", Step.class));
        assertTrue(step2 == variableStatementResolver.resolveStatement("a x c", Step.class));
        assertTrue(step3 == variableStatementResolver.resolveStatement("a b x", Step.class));
    }

    @Test
    public void testResolveStatementWithVariableWithMultipleKnownResults() {
        Result result1 = noResult();
        Result result2 = noResult();
        Result result3 = noResult();
        variableStatementResolver.addStatement("${a} b c", result1, Result.class);
        variableStatementResolver.addStatement("a ${b} c", result2, Result.class);
        variableStatementResolver.addStatement("a b ${c}", result3, Result.class);
        assertTrue(result1 == variableStatementResolver.resolveStatement("x b c", Result.class));
        assertTrue(result2 == variableStatementResolver.resolveStatement("a x c", Result.class));
        assertTrue(result3 == variableStatementResolver.resolveStatement("a b x", Result.class));
    }

    @Test
    public void testInjectVariables() {
        VariableAwareFact factMock = mock(VariableAwareFact.class);
        variableStatementResolver.addStatement("a ${b} c", factMock, Fact.class);
        assertEquals(factMock, variableStatementResolver.resolveStatement("a value c", Fact.class));
        Map<String, String> expectedVariables = new HashMap<String, String>();
        expectedVariables.put("b", "value");
        verify(factMock, times(1)).setVariables(expectedVariables);
        verifyNoMoreInteractions(factMock);
    }

    private static class VariableAwareFact implements Fact, VariablesAware {
        @Override
        public void establish() {
            // Intentionally left blank.
        }

        @Override
        public void setVariables(Map<String, String> variables) {
            // Intentionally left blank.
        }
    }
}
