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

import de.codecentric.zucchini.bdd.vars.AbstractVariable;
import de.codecentric.zucchini.web.pageobjects.PageObject;

import static de.codecentric.zucchini.web.pageobjects.SimplePage.url;

public class PageObjectVariable extends AbstractVariable<PageObject> {
    public PageObjectVariable(String name) {
        super(name);
    }

    @Override
    public PageObject convert(String value) {
        return url(value);
    }
}
