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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParameterizedBy extends By implements VariablesAware {
    private Class<? extends By> byClass;
    private String selector;
    private Map<String, String> variables;

    public static ParameterizedBy element(Class<? extends By> byClass, String selector) {
        return new ParameterizedBy(byClass, selector);
    }

    /**
     * Initializes a parameterized by.
     * 
     * @param byClass The implementation of {@link org.openqa.selenium.By} that has a {@link java.lang.String}
     *                constructor.
     * @param variableName The name of the variable that contains the selector.
     */
    public ParameterizedBy(Class<? extends By> byClass, String variableName) {
        this.byClass = byClass;
        this.selector = variableName;
    }

    @Override
    public List<WebElement> findElements(SearchContext context) {
        try {
            return byClass.getConstructor(String.class).newInstance(variables.get(selector)).findElements(context);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return new ArrayList<WebElement>();
    }

    @Override
    public void setVariables(Map<String, String> variables) {
        this.variables = variables;
    }
}