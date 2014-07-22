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

import de.codecentric.zucchini.bdd.dsl.Step;
import de.codecentric.zucchini.web.WebDriverAware;

/**
 * A web step is a specialized {@link de.codecentric.zucchini.bdd.dsl.Step} that is aware of a
 * {@link org.openqa.selenium.WebDriver}, i.e. web steps can use the web driver to manipulate and interact with web
 * pages.
 */
public interface WebStep extends Step, WebDriverAware {
}
