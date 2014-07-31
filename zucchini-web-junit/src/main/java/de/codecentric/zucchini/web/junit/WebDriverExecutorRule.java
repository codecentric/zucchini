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

package de.codecentric.zucchini.web.junit;

import de.codecentric.zucchini.bdd.ExecutorHolder;
import de.codecentric.zucchini.web.WebDriverExecutor;
import de.codecentric.zucchini.web.provider.WebDriverProvider;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * This JUnit {@link org.junit.rules.TestRule rule} provides a mechanism for defining a
 * {@link de.codecentric.zucchini.web.WebDriverExecutor web driver executor} either by directly injecting a custom
 * {@link de.codecentric.zucchini.web.WebDriverExecutor web driver executor}, or by specifying a
 * {@link de.codecentric.zucchini.web.provider.WebDriverProvider web driver provider} which is then used with the
 * default {@link de.codecentric.zucchini.web.WebDriverExecutor web driver executor}, or by specifying nothing which
 * results in the default {@link de.codecentric.zucchini.web.WebDriverExecutor web driver executor} using the default
 * {@link de.codecentric.zucchini.web.provider.WebDriverProvider web driver provider}.
 */
public class WebDriverExecutorRule implements TestRule {
    private WebDriverExecutor webDriverExecutor;

    private WebDriverProvider webDriverProvider;

    /**
     * Initializes a web driver executor rule that uses the default
     * {@link de.codecentric.zucchini.web.WebDriverExecutor web driver executor} using the default
     * {@link de.codecentric.zucchini.web.provider.WebDriverProvider web driver provider}.
     */
    public WebDriverExecutorRule() {
    }

    /**
     * Initializes a web driver executor rule that uses the specified
     * {@link de.codecentric.zucchini.web.WebDriverExecutor web driver executor}.
     *
     * @param webDriverExecutor The {@link de.codecentric.zucchini.web.WebDriverExecutor web driver executor}.
     */
    public WebDriverExecutorRule(WebDriverExecutor webDriverExecutor) {
        this.webDriverExecutor = webDriverExecutor;
    }

    /**
     * Initializes a web driver executor rule that uses the specified
     * {@link de.codecentric.zucchini.web.provider.WebDriverProvider web driver provider} with the default
     * {@link de.codecentric.zucchini.web.WebDriverExecutor web driver executor}.
     *
     * @param webDriverProvider The {@link de.codecentric.zucchini.web.WebDriverExecutor web driver executor}.
     */
    public WebDriverExecutorRule(WebDriverProvider webDriverProvider) {
        this.webDriverProvider = webDriverProvider;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * Returns the {@link de.codecentric.zucchini.web.WebDriverExecutor web driver executor}.
     *
     * @return The {@link de.codecentric.zucchini.web.WebDriverExecutor web driver executor}.
     */
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
