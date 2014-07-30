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

import de.codecentric.zucchini.bdd.dsl.*;
import de.codecentric.zucchini.bdd.resolver.token.TokenList;
import de.codecentric.zucchini.bdd.resolver.token.Tokenizer;

import java.util.HashMap;
import java.util.Map;

public class VariableStatementResolver implements StatementResolver {
    private final Map<Class<? extends Statement>, Map<TokenList, Statement>> statements = new HashMap<Class<? extends Statement>, Map<TokenList, Statement>>();

    private final Tokenizer tokenizer = new Tokenizer();

    /**
     * Initializes the statement resolver.
     */
    public VariableStatementResolver() {
        statements.put(Fact.class, new HashMap<TokenList, Statement>());
        statements.put(Step.class, new HashMap<TokenList, Statement>());
        statements.put(Result.class, new HashMap<TokenList, Statement>());
    }

    @Override
    public void addStatement(String statementName, Statement statement, Class<? extends Statement> type) {
        statements.get(type).put(tokenizer.tokenize(statementName), statement);
    }

    @Override
    public <T extends Statement> T resolveStatement(String statementName, Class<T> type) {
        TokenList tokenizedStatementName = tokenizer.tokenize(statementName);
        Map.Entry<TokenList, Statement> entry = resolveStatementEntry(tokenizedStatementName, type);
        if (entry == null) {
            throw new UnknownStatementException(String.format("Could not find a statement with the name %s.", statementName));
        }
        Statement statement = entry.getValue();
        injectVariables(entry.getKey().detectVariableValues(tokenizedStatementName), statement);
        return type.cast(statement);
    }

    private <T extends Statement> Map.Entry<TokenList, Statement> resolveStatementEntry(TokenList tokenizedStatementName, Class<T> type) {
        for (Map.Entry<TokenList, Statement> entry : statements.get(type).entrySet()) {
            if (entry.getKey().equals(tokenizedStatementName)) {
                return entry;
            }
        }
        return null;
    }
    
    private void injectVariables(Map<String, String> variables, Statement statement) {
        if (statement instanceof VariablesAware) {
            ((VariablesAware) statement).setVariables(variables);
        }
    }
}
