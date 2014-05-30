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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.thoughtworks.selenium.SeleneseTestBase.assertFalse;

public class NotResult extends AbstractWebResult {
	private static final Logger logger = LoggerFactory.getLogger(NotResult.class);

	private WebResult webResult;

	public NotResult(WebResult webResult) {
		this.webResult = webResult;
	}

	@Override
	public void expect() {
		try {
			webResult.setWebDriver(getWebDriver());
			webResult.expect();
			assertFalse(true);
		} catch (Exception e) {
			logger.debug("Negated failure:", e);
		} catch (AssertionError e) {
			logger.debug("Negated failure:", e);
		}
	}
}
