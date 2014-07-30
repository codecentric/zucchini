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

package de.codecentric.zucchini.bdd.resolver.token;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TokenListTest {
    @Test
    public void testEqualsWithLiteralsOnly() {
        TokenList tokens1 = new TokenList();
        tokens1.add(new LiteralToken("a"));
        tokens1.add(new LiteralToken("b"));
        tokens1.add(new LiteralToken("c"));
        TokenList tokens2 = new TokenList();
        tokens2.add(new LiteralToken("a"));
        tokens2.add(new LiteralToken("b"));
        tokens2.add(new LiteralToken("c"));
        assertEquals(tokens1, tokens2);
    }

    @Test
    public void testEqualsWithVariablesOnly() {
        TokenList tokens1 = new TokenList();
        tokens1.add(new VariableToken("a"));
        tokens1.add(new VariableToken("b"));
        tokens1.add(new VariableToken("c"));
        TokenList tokens2 = new TokenList();
        tokens2.add(new VariableToken("a"));
        tokens2.add(new VariableToken("b"));
        tokens2.add(new VariableToken("c"));
        assertEquals(tokens1, tokens2);
    }

    @Test
    public void testEqualsWithLiteralsAndVariablesOnSamePosition() {
        TokenList tokens1 = new TokenList();
        tokens1.add(new LiteralToken("a"));
        tokens1.add(new VariableToken("b"));
        tokens1.add(new VariableToken("c"));
        TokenList tokens2 = new TokenList();
        tokens2.add(new LiteralToken("a"));
        tokens2.add(new VariableToken("b"));
        tokens2.add(new VariableToken("c"));
        assertEquals(tokens1, tokens2);
    }

    @Test
    public void testEqualsWithLiteralsAndVariablesOnDifferentPosition() {
        TokenList tokens1 = new TokenList();
        tokens1.add(new VariableToken("a"));
        tokens1.add(new LiteralToken("b"));
        tokens1.add(new VariableToken("c"));
        TokenList tokens2 = new TokenList();
        tokens2.add(new LiteralToken("a"));
        tokens2.add(new VariableToken("b"));
        tokens2.add(new VariableToken("c"));
        assertEquals(tokens1, tokens2);
    }

    @Test
    public void testNotEqualsWithLiteralsOnly() {
        TokenList tokens1 = new TokenList();
        tokens1.add(new LiteralToken("a"));
        tokens1.add(new LiteralToken("b"));
        tokens1.add(new LiteralToken("c"));
        TokenList tokens2 = new TokenList();
        tokens2.add(new LiteralToken("a"));
        tokens2.add(new LiteralToken("b"));
        tokens2.add(new LiteralToken("ZZZ"));
        assertNotEquals(tokens1, tokens2);
    }

    @Test
    public void testNotEqualsWithVariablesOnly() {
        TokenList tokens1 = new TokenList();
        tokens1.add(new VariableToken("a"));
        tokens1.add(new VariableToken("b"));
        tokens1.add(new VariableToken("c"));
        TokenList tokens2 = new TokenList();
        tokens2.add(new VariableToken("a"));
        tokens2.add(new VariableToken("b"));
        tokens2.add(new VariableToken("ZZZ"));
        assertNotEquals(tokens1, tokens2);
    }

    @Test
    public void testNotEqualsWithLiteralsAndVariables() {
        TokenList tokens1 = new TokenList();
        tokens1.add(new VariableToken("a"));
        tokens1.add(new VariableToken("b"));
        tokens1.add(new VariableToken("c"));
        TokenList tokens2 = new TokenList();
        tokens2.add(new VariableToken("a"));
        tokens2.add(new VariableToken("b"));
        tokens2.add(new LiteralToken("ZZZ"));
        assertNotEquals(tokens1, tokens2);
    }
}
