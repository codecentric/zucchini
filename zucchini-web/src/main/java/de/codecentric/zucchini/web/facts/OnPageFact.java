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

package de.codecentric.zucchini.web.facts;

import de.codecentric.zucchini.bdd.vars.Variable;
import de.codecentric.zucchini.web.pageobjects.PageObject;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * The on page fact is used to navigate to a page described by a
 * {@link de.codecentric.zucchini.web.pageobjects.PageObject page object}.
 *
 * Page objects that are used with this fact may use annotations like {@link org.openqa.selenium.support.FindBy} on
 * fields to inject {@link org.openqa.selenium.WebElement elements} of the page.
 *
 * @see <a href="https://code.google.com/p/selenium/wiki/PageFactory">Selenium PageFactory</a>
 */
public class OnPageFact extends AbstractWebFact {
    private static final Logger logger = LoggerFactory.getLogger(OnPageFact.class);

    private PageObject pageObject;

    private Class<? extends PageObject> pageObjectClass;

    private Variable<PageObject> pageObjectVariable;

    private boolean isInitialized = false;

    /**
     * Initializes a new on page fact.
     *
     * @param pageObject The page object.
     * @see de.codecentric.zucchini.web.facts.WebFacts#onPage(de.codecentric.zucchini.web.pageobjects.PageObject)
     */
    public OnPageFact(PageObject pageObject) {
        if (pageObject == null) {
            throw new NullPointerException("You must specify a valid page object.");
        }
        this.pageObject = pageObject;
    }

    /**
     * Initializes a new on page fact.
     *
     * @param pageObjectVariable A variable containing the page object.
     * @see de.codecentric.zucchini.web.facts.WebFacts#onPage(de.codecentric.zucchini.web.pageobjects.PageObject)
     */
    public OnPageFact(Variable<PageObject> pageObjectVariable) {
        if (pageObjectVariable == null) {
            throw new NullPointerException("You must specify a valid page object variable.");
        }
        this.pageObjectVariable = pageObjectVariable;
    }

    /**
     * Initializes a new on page fact.
     *
     * @param pageObjectClass The page object class.
     * @see de.codecentric.zucchini.web.facts.WebFacts#onPage(Class)
     */
    public OnPageFact(Class<? extends PageObject> pageObjectClass) {
        if (pageObjectClass == null) {
            throw new NullPointerException("You must specify a valid page object class.");
        }
        this.pageObjectClass = pageObjectClass;
    }

    /**
     * Opens the page described by the page object of this fact.
     */
    @Override
    public void establish() {
        logger.info("Opening page {}...", pageObject);
        initializePage();
        pageObject.open();
    }

    @Override
    public void setVariables(Map<String, String> variables) {
        if (pageObjectVariable != null) {
            pageObject = pageObjectVariable.getConvertedValue(variables);
        }
    }

    private void initializePage() {
        if (!isInitialized) {
            isInitialized = true;
            if (pageObject != null) {
                PageFactory.initElements(getWebDriver(), pageObject);
            } else if (pageObjectClass != null) {
                pageObject = PageFactory.initElements(getWebDriver(), pageObjectClass);
            }
        }
        if (pageObject != null) {
            pageObject.setWebDriver(getWebDriver());
        }
    }
}
