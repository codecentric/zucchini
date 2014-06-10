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
import de.codecentric.zucchini.web.facts.WebFact;
import de.codecentric.zucchini.web.provider.HtmlUnitDriverProvider;
import de.codecentric.zucchini.web.provider.WebDriverProvider;
import de.codecentric.zucchini.web.results.WebResult;
import de.codecentric.zucchini.web.steps.WebStep;

public class WebDriverExecutor extends AbstractExecutor {
	private WebDriverProvider webDriverProvider;

	public WebDriverExecutor() {
		this(new HtmlUnitDriverProvider());
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
		if (statement instanceof WebDriverAware) {
			((WebDriverAware) statement).setWebDriver(webDriverProvider.getWebDriver());
		}
	}

	protected void failOnInvalidContext(ExecutionContext executionContext) {
		for (Fact fact : executionContext.getFacts()) {
			if (!(fact instanceof WebFact) && !(fact instanceof ExecutionFact)) {
				throw new ExecutionException("Invalid facts detected. The WebDriverExecutor only supports WebFact implementations.");
			}
		}
		for (Step step : executionContext.getSteps()) {
			if (!(step instanceof WebStep)) {
				throw new ExecutionException("Invalid step detected. The WebDriverExecutor only supports WebCondition implementations.");
			}
		}
		for (Result result : executionContext.getResults()) {
			if (!(result instanceof WebResult)) {
				throw new ExecutionException("Invalid result detected. The WebDriverExecutor only supports WebResult implementations.");
			}
		}
	}
}
