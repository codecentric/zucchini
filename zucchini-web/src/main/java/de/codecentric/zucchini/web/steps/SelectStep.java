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

package de.codecentric.zucchini.web.steps;

import de.codecentric.zucchini.bdd.vars.IntegerVariable;
import de.codecentric.zucchini.bdd.vars.StringVariable;
import de.codecentric.zucchini.bdd.vars.Variable;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static de.codecentric.zucchini.web.util.WebAssert.findElementOrFail;

/**
 * A select step selects an option of a specific {@link org.openqa.selenium.WebElement element} described by
 * {@link org.openqa.selenium.By}.
 *
 * @see de.codecentric.zucchini.web.steps.WebSteps#select(org.openqa.selenium.By)
 * @see de.codecentric.zucchini.web.steps.SelectContext
 */
public class SelectStep extends AbstractWebStep {
    private static final Logger logger = LoggerFactory.getLogger(SelectStep.class);

    /**
     * This enum is used to define which type of selector is used.
     */
    public enum OptionSelectorType {
        /**
         * The selector is the value of the option.
         */
        VALUE,

        /**
         * The selector is the text of the option.
         */
        TEXT,

        /**
         * The selector is the index of the option.
         */
        INDEX
    }

    private final By element;

    private Object optionSelector;

    private final OptionSelectorType optionSelectorType;

    /**
     * Initializes a select step.
     *
     * @param element            The element that has options.
     * @param optionSelector     The selector (a specific index, text, or value, depending on the
     *                           {@link de.codecentric.zucchini.web.steps.SelectStep.OptionSelectorType}.
     * @param optionSelectorType The type of the selector.
     */
    public SelectStep(By element, Object optionSelector, OptionSelectorType optionSelectorType) {
        this.element = element;
        this.optionSelector = optionSelector;
        this.optionSelectorType = optionSelectorType;
    }

    /**
     * Selects an option of the {@link org.openqa.selenium.WebElement element} described by
     * {@link org.openqa.selenium.By}.
     */
    @Override
    public void go() {
        logger.info("Waiting for select {}...", element);
        Select select = new Select(findElementOrFail(getWebDriver(), element));
        if (OptionSelectorType.INDEX.equals(optionSelectorType)) {
            logger.info("Selecting index {}...", optionSelector);
            select.selectByIndex((Integer) optionSelector);
        } else if (OptionSelectorType.VALUE.equals(optionSelectorType)) {
            logger.info("Selecting value {}...", optionSelector);
            select.selectByValue((String) optionSelector);
        } else if (OptionSelectorType.TEXT.equals(optionSelectorType)) {
            logger.info("Selecting text {}...", optionSelector);
            select.selectByVisibleText((String) optionSelector);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVariables(Map<String, String> variables) {
        injectVariables(variables, element);

        /**
         * This is valid:
         * <code>
         *     select(element(ById.class, "element-id"})).index(intVar("element-index"))
         * </code>
         * and it will be resolved to
         */
        if (optionSelector instanceof StringVariable) {
            Variable<String> variable = (StringVariable) optionSelector;
            optionSelector = variable.getConvertedValue(variables);
        } else if (optionSelector instanceof IntegerVariable) {
            Variable<Integer> variable = (IntegerVariable) optionSelector;
            optionSelector = variable.getConvertedValue(variables);
        }
    }
}
