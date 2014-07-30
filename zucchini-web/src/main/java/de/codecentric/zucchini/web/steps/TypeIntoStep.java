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

import de.codecentric.zucchini.bdd.vars.Variable;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;

import static de.codecentric.zucchini.web.util.WebAssert.findElementOrFail;

<<<<<<<HEAD
        =======
        >>>>>>>Issue #8:Added support for variable substitution.

/**
 * A type into step types keys (text or a key combination) into a specific
 * {@link org.openqa.selenium.WebElement element} described by {@link org.openqa.selenium.By}.
 *
 * @see de.codecentric.zucchini.web.steps.WebSteps#type(java.lang.CharSequence[])
 * @see de.codecentric.zucchini.web.steps.TypeContext#into(org.openqa.selenium.By)
 */
public class TypeIntoStep extends AbstractWebStep {
    private static final Logger logger = LoggerFactory.getLogger(TypeIntoStep.class);

    private CharSequence[] keys;
    private Variable<CharSequence[]> keysVariable;
    private By element;

    /**
     * Initializes a type into step.
     *
     * @param keys    The keys that shall be typed.
     * @param element The element in which the keys shall be typed.
     */
    public TypeIntoStep(CharSequence[] keys, By element) {
        this.keys = Arrays.copyOf(keys, keys.length);
        this.element = element;
    }

    /**
     * Initializes a type into step.
     *
     * @param keysVariable The variable that contains the keys that shall be typed.
     * @param element      The element in which the keys shall be typed.
     */
    public TypeIntoStep(Variable<CharSequence[]> keysVariable, By element) {
        this.keysVariable = keysVariable;
        this.element = element;
    }

    /**
     * Types into the element.
     */
    @Override
    public void go() {
        logger.info("Typing \"{}\" into {}...", keys, element);
        findElementOrFail(getWebDriver(), element).sendKeys(keys);
    }

    @Override
    public void setVariables(Map<String, String> variables) {
        injectVariables(variables, element);
        if (keysVariable != null) {
            keys = keysVariable.convert(variables.get(keysVariable.getName()));
        }
    }
}
