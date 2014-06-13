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

package de.codecentric.zucchini.web;

import de.codecentric.zucchini.bdd.AbstractExecutor;
import de.codecentric.zucchini.bdd.ExecutionContext;
import de.codecentric.zucchini.bdd.ExecutionException;
import de.codecentric.zucchini.bdd.dsl.*;
import de.codecentric.zucchini.bdd.dsl.impl.facts.DelegatingFact;
import de.codecentric.zucchini.bdd.dsl.impl.results.DelegatingResult;
import de.codecentric.zucchini.bdd.dsl.impl.steps.DelegatingStep;
import de.codecentric.zucchini.web.facts.WebFact;
import de.codecentric.zucchini.web.provider.ChromeDriverProvider;
import de.codecentric.zucchini.web.provider.WebDriverProvider;
import de.codecentric.zucchini.web.results.WebResult;
import de.codecentric.zucchini.web.steps.WebStep;

public class WebDriverExecutor extends AbstractExecutor {
	private WebDriverProvider webDriverProvider;

	public WebDriverExecutor() {
		this(new ChromeDriverProvider());
	}

	public WebDriverExecutor(WebDriverProvider webDriverProvider) {
		if (webDriverProvider == null) {
			throw new NullPointerException("The WebDriverExecutor expects the WebDriverProvider to be non-null.");
		}
		this.webDriverProvider = webDriverProvider;
	}

	public WebDriverProvider getWebDriverProvider() {
		return webDriverProvider;
	}

	protected void initialize() {
		webDriverProvider.startWebDriver();
	}

	protected void shutdown() {
		webDriverProvider.stopWebDriver();
	}

	@Override
	protected void prepareStatement(Statement statement) {
		if (statement instanceof DelegatingStatement) {
			injectWebDriver(((DelegatingStatement) statement).getStatement());
		} else {
			injectWebDriver(statement);
		}
	}

	private void injectWebDriver(Statement statement) {
		if (statement instanceof WebDriverAware) {
			((WebDriverAware) statement).setWebDriver(webDriverProvider.getWebDriver());
		}
	}

	protected void failOnInvalidContext(ExecutionContext executionContext) {
		for (Fact fact : executionContext.getFacts()) {
			if (fact instanceof DelegatingFact) {
				DelegatingFact delegatingFact = (DelegatingFact) fact;
				failOnInvalidFact(delegatingFact.getStatement());
			} else {
				failOnInvalidFact(fact);
			}
		}
		for (Step step : executionContext.getSteps()) {
			if (step instanceof DelegatingStep) {
				DelegatingStep delegatingStep = (DelegatingStep) step;
				failOnInvalidStep(delegatingStep.getStatement());
			} else {
				failOnInvalidStep(step);
			}
		}
		for (Result result : executionContext.getResults()) {
			if (result instanceof DelegatingResult) {
				DelegatingResult delegatingResult = (DelegatingResult) result;
				failOnInvalidResult(delegatingResult.getStatement());
			} else {
				failOnInvalidResult(result);
			}
		}
	}

	private void failOnInvalidFact(Fact fact) {
		if (!(fact instanceof WebFact) && !(fact instanceof ExecutionFact)) {
			throw new ExecutionException("Invalid facts detected. The WebDriverExecutor only supports WebFact implementations.");
		}
	}

	private void failOnInvalidStep(Step step) {
		if (!(step instanceof WebStep)) {
			throw new ExecutionException("Invalid step detected. The WebDriverExecutor only supports WebCondition implementations.");
		}
	}

	private void failOnInvalidResult(Result result) {
		if (!(result instanceof WebResult)) {
			throw new ExecutionException("Invalid result detected. The WebDriverExecutor only supports WebResult implementations.");
		}
	}
}
