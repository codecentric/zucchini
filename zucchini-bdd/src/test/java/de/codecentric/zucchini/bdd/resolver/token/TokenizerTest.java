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

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TokenizerTest {
    private Tokenizer tokenizer;

    @Before
    public void setUp() {
        tokenizer = new Tokenizer();
    }

    @Test
    public void testTokenize1() {
        List<Token> tokens = tokenizer.tokenize("Lorem ipsum ${a}${b} dolor ${c} sit amet.");
        assertEquals(8, tokens.size());
        assertToken(LiteralToken.class, "Lorem", tokens.get(0));
        assertToken(LiteralToken.class, "ipsum", tokens.get(1));
        assertToken(VariableToken.class, "a", tokens.get(2));
        assertToken(VariableToken.class, "b", tokens.get(3));
        assertToken(LiteralToken.class, "dolor", tokens.get(4));
        assertToken(VariableToken.class, "c", tokens.get(5));
        assertToken(LiteralToken.class, "sit", tokens.get(6));
        assertToken(LiteralToken.class, "amet", tokens.get(7));
    }

    @Test
    public void testTokenize2() {
        List<Token> tokens = tokenizer.tokenize("${a}${b}${c}");
        assertEquals(3, tokens.size());
        assertToken(VariableToken.class, "a", tokens.get(0));
        assertToken(VariableToken.class, "b", tokens.get(1));
        assertToken(VariableToken.class, "c", tokens.get(2));
    }

    @Test
    public void testTokenize3() {
        List<Token> tokens = tokenizer.tokenize("${a_b-c.1}");
        assertEquals(1, tokens.size());
        assertToken(VariableToken.class, "a_b-c.1", tokens.get(0));
    }

    @Test
    public void testTokenize4() {
        List<Token> tokens = tokenizer.tokenize("Magic happens \"at codecentric\"");
        assertEquals(3, tokens.size());
        assertToken(LiteralToken.class, "Magic", tokens.get(0));
        assertToken(LiteralToken.class, "happens", tokens.get(1));
        assertToken(LiteralToken.class, "at codecentric", tokens.get(2));
    }

    @Test(expected = RuntimeException.class)
    public void testTokenizeInvalid1() {
        tokenizer.tokenize("${");
    }

    @Test(expected = RuntimeException.class)
    public void testTokenizeInvalid2() {
        tokenizer.tokenize("}");
    }

    @Test(expected = RuntimeException.class)
    public void testTokenizeInvalid3() {
        tokenizer.tokenize("${}");
    }

    @Test(expected = RuntimeException.class)
    public void testTokenizeInvalid4() {
        tokenizer.tokenize("${${");
    }

    @Test(expected = RuntimeException.class)
    public void testTokenizeInvalid5() {
        tokenizer.tokenize("}${");
    }

    @Test(expected = RuntimeException.class)
    public void testTokenizeInvalid6() {
        tokenizer.tokenize("${a b}");
    }

    @Test(expected = RuntimeException.class)
    public void testTokenizeInvalid7() {
        tokenizer.tokenize("${b}}");
    }

    @Test(expected = RuntimeException.class)
    public void testTokenizeInvalid8() {
        tokenizer.tokenize("${1}");
    }

    @Test(expected = RuntimeException.class)
    public void testTokenizeInvalid9() {
        tokenizer.tokenize("${.}");
    }

    @Test(expected = RuntimeException.class)
    public void testTokenizeInvalid10() {
        tokenizer.tokenize("${-}");
    }

    @Test(expected = RuntimeException.class)
    public void testTokenizeInvalid11() {
        tokenizer.tokenize("${_}");
    }

    @Test
    public void testCompareTokenized1() {
        TokenList tokensWithVariable = tokenizer.tokenize("id-${a}-01");
        TokenList tokensWithLiteralsOnly = tokenizer.tokenize("id-zero-01");
        assertEquals(tokensWithVariable, tokensWithLiteralsOnly);
    }

    @Test
    public void testCompareTokenized2() {
        TokenList tokensWithVariable = tokenizer.tokenize("Magic happens \"${here}\"");
        TokenList tokensWithLiteralsOnly = tokenizer.tokenize("Magic happens \"at codecentric\"");
        assertEquals(tokensWithVariable, tokensWithLiteralsOnly);
    }

    @Test
    public void testCompareTokenized3() {
        TokenList tokensWithVariable = tokenizer.tokenize("Magic happens \"${here}\" and \"${somewhere_else}\".");
        TokenList tokensWithLiteralsOnly = tokenizer.tokenize("Magic happens \"at codecentric\" and Narnia.");
        assertEquals(tokensWithVariable, tokensWithLiteralsOnly);
    }

    private void assertToken(Class<? extends Token> tokenType, String expectedText, Token token) {
        assertNotNull(token);
        assertTrue(tokenType.isAssignableFrom(token.getClass()));
        assertEquals(expectedText, token.getText());
    }
}
