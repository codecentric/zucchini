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

public class WaitStep extends AbstractWebStep {
	private static final long DEFAULT_SLEEP_TIME = 10;

	private long sleepTime;

	public WaitStep() {
		this.sleepTime = DEFAULT_SLEEP_TIME;
	}

	public WaitStep(long sleepTime) {
		this.sleepTime = sleepTime;
	}

	public WaitStep withSleepTime(long sleepTime) {
		this.sleepTime = sleepTime;
		return this;
	}

	@Override
	public void go() {
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException ie) {
			throw new RuntimeException("Could not sleep thread.", ie);
		}
	}
}
