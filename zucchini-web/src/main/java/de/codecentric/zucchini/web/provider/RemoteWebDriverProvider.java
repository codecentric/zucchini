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

package de.codecentric.zucchini.web.provider;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * This web driver provider can be used to integrate any remote web driver.
 *
 * This class allows you to use your own remote web driver or more sophisticated web drivers like the Ghost Driver/
 * PhantomJSDriver.
 *
 * @see <a href="https://github.com/detro/ghostdriver">Ghost Driver</a>
 */
public class RemoteWebDriverProvider extends AbstractWebDriverProvider {
    private RemoteWebDriver remoteWebDriver;

    public RemoteWebDriverProvider(RemoteWebDriver remoteWebDriver) {
        this.remoteWebDriver = remoteWebDriver;
    }

    @Override
    protected WebDriver createWebDriver() {
        return remoteWebDriver;
    }
}
