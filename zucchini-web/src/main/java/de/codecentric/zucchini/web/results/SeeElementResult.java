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

import org.openqa.selenium.By;

import java.util.Map;

import static de.codecentric.zucchini.web.util.WebAssert.findElementOrFail;

/**
 * A see result expects that a specific element is present on a page.
 */
public class SeeElementResult extends AbstractWebResult {
    private By element;

    /**
     * Initializes a see element result.
     *
     * @param element The element that shall be expected to be present.
     */
    public SeeElementResult(By element) {
        this.element = element;
    }

    /**
     * Expects that the specified element is present.
     */
    @Override
    public void expect() {
        findElementOrFail(getWebDriver(), element);
    }

    @Override
    public void setVariables(Map<String, String> variables) {
        injectVariables(variables, element);
    }
}
