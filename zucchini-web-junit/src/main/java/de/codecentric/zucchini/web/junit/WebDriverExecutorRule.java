package de.codecentric.zucchini.web.junit;/*
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

import de.codecentric.zucchini.bdd.ExecutorHolder;
import de.codecentric.zucchini.web.WebDriverExecutor;
import de.codecentric.zucchini.web.provider.WebDriverProvider;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class WebDriverExecutorRule implements TestRule {

	private WebDriverExecutor webDriverExecutor;

	private WebDriverProvider webDriverProvider;

	public WebDriverExecutorRule() {
	}

	public WebDriverExecutorRule(WebDriverExecutor webDriverExecutor) {
		this.webDriverExecutor = webDriverExecutor;
	}

	public WebDriverExecutorRule(WebDriverProvider webDriverProvider) {
		this.webDriverProvider = webDriverProvider;
	}

	@Override
	public Statement apply(final Statement base, Description description) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				ExecutorHolder.setExecutor(getWebDriverExecutor());
				base.evaluate();
			}
		};
	}

	public WebDriverExecutor getWebDriverExecutor() {
		if (webDriverExecutor == null) {
			if (webDriverProvider == null) {
				webDriverExecutor = new WebDriverExecutor();
			} else {
				webDriverExecutor = new WebDriverExecutor(webDriverProvider);
			}
		}
		return webDriverExecutor;
	}
}
