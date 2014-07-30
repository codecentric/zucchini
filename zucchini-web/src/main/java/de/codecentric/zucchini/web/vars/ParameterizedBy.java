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

package de.codecentric.zucchini.web.vars;

import de.codecentric.zucchini.bdd.dsl.VariablesAware;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * This class provides a simple mechanism to create {@link org.openqa.selenium.By} instances using variables (hence
 * parameterized).
 */
public class ParameterizedBy extends By implements VariablesAware {
    private final Class<? extends By> byClass;
    private final String variableName;
    private Map<String, String> variables;

    /**
     * Creates a {@link org.openqa.selenium.By} that uses a variable for its value.
     *
     * Example:
     * <code>
     * given(...)
     * .when(click(element(ById.class, "id-variable")))
     * .then(...)
     * .end();
     * </code>
     *
     * @param byClass
     * @param variableName
     * @return
     */
    public static ParameterizedBy element(Class<? extends By> byClass, String variableName) {
        return new ParameterizedBy(byClass, variableName);
    }

    /**
     * Initializes a parameterized by.
     *
     * @param byClass      The implementation of {@link org.openqa.selenium.By} that has a {@link java.lang.String}
     *                     constructor.
     * @param variableName The name of the variable that contains the selector.
     */
    @SuppressWarnings("WeakerAccess")
    public ParameterizedBy(Class<? extends By> byClass, String variableName) {
        this.byClass = byClass;
        this.variableName = variableName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<WebElement> findElements(SearchContext context) {
        try {
            return byClass.getConstructor(String.class).newInstance(variables.get(variableName)).findElements(context);
        } catch (InstantiationException e) {
            throw new ParameterizationException(String.format("Cannot instantiate %s (is it abstract or an interface?).", byClass.getName()), e);
        } catch (IllegalAccessException e) {
            throw new ParameterizationException(String.format("Cannot access constructor of %s.", byClass.getName()), e);
        } catch (InvocationTargetException e) {
            throw new ParameterizationException(String.format("Cannot invoke constructor of %s.", byClass.getName()), e);
        } catch (NoSuchMethodException e) {
            throw new ParameterizationException(String.format("%s does not have a constructor with one string parameter.", byClass.getName()), e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVariables(Map<String, String> variables) {
        this.variables = variables;
    }
}