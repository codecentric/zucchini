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

package de.codecentric.zucchini.web.results;

import de.codecentric.zucchini.bdd.dsl.VariablesAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static com.thoughtworks.selenium.SeleneseTestBase.fail;

/**
 * The not result inverts other results and expects them to fail.
 *
 * For example:
 * <code>
 * given(...)
 * .when(...)
 * .then(not(see("This text should not be on the page.")))
 * .end();
 * </code>
 */
public class NotResult extends AbstractWebResult {
    private static final Logger logger = LoggerFactory.getLogger(NotResult.class);

    private WebResult webResult;

    /**
     * Initializes a not result.
     *
     * @param webResult The result which shall be inverted.
     */
    public NotResult(WebResult webResult) {
        this.webResult = webResult;
    }

    /**
     * Expects that the specified web result fails.
     */
    @Override
    public void expect() {
        try {
            webResult.setWebDriver(getWebDriver());
            webResult.expect();
            fail("Result should fail but it did not.");
        } catch (Exception e) {
            logger.debug("Negated failure:", e);
        } catch (AssertionError e) {
            logger.debug("Negated failure:", e);
        }
    }

    @Override
    public void setVariables(Map<String, String> variables) {
        if (webResult instanceof VariablesAware) {
            ((VariablesAware) webResult).setVariables(variables);
        }
    }
}
