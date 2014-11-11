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

import de.codecentric.zucchini.bdd.vars.Variable;
import org.openqa.selenium.By;

/**
 * This utility class provides methods that return pre-configured web results.
 */
public class WebResults {
	/**
	 * Returns a non-operational web result.
	 *
	 * @return A non-operational web result.
	 */
	public static NonOperationalWebResult noOpWebResult() {
		return new NonOperationalWebResult();
	}

	/**
	 * Returns an {@link de.codecentric.zucchini.web.results.InputContext input context}.
	 *
	 * @param element The element whose input state shall be expected.
	 * @return An {@link de.codecentric.zucchini.web.results.InputContext input context}.
	 * @see de.codecentric.zucchini.web.results.InputDisabledResult
	 * @see de.codecentric.zucchini.web.results.InputReadOnlyResult
	 * @see de.codecentric.zucchini.web.results.InputValueResult
	 */
	public static InputContext input(By element) {
		return new InputContext(element);
	}

    /**
     * Returns a {@link de.codecentric.zucchini.web.results.SeeResult see result}.
     *
     * @param text The text that shall be expected to be present.
     * @return A {@link de.codecentric.zucchini.web.results.SeeResult see result}.
     */
    public static SeeResult see(String text) {
        return new SeeResult(text);
    }

    /**
     * Returns a {@link de.codecentric.zucchini.web.results.SeeResult see result}.
     *
     * @param textVariable A variable containing the text that shall be expected to be present.
     * @return A {@link de.codecentric.zucchini.web.results.SeeResult see result}.
     */
    public static SeeResult see(Variable<String> textVariable) {
        return new SeeResult(textVariable);
    }

    /**
     * Returns a {@link de.codecentric.zucchini.web.results.SeeElementResult see element result}.
     *
     * @param element The element that shall be expected to be present.
     * @return A {@link de.codecentric.zucchini.web.results.SeeElementResult see element result}.
     */
    public static SeeElementResult see(By element) {
        return new SeeElementResult(element);
    }

	/**
	 * Returns a {@link de.codecentric.zucchini.web.results.NotResult not result}.
	 *
	 * @param webResult The result which shall be inverted.
	 * @return A {@link de.codecentric.zucchini.web.results.NotResult not result}.
	 */
	public static WebResult not(WebResult webResult) {
		return new NotResult(webResult);
	}
    
    private WebResults() {
    }
}
