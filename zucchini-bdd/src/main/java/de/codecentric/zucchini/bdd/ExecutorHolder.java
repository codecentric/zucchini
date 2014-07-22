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

package de.codecentric.zucchini.bdd;

/**
 * The executor holder is used to define an {@link de.codecentric.zucchini.bdd.Executor} which is used for the execution
 * of statements within the current thread.
 * <p/>
 * If no executor is registered, the {@link de.codecentric.zucchini.bdd.NonOperationalExecutor} will be used which does
 * not execute anything.
 */
public class ExecutorHolder {
	private static final ThreadLocal<Executor> executor = new ThreadLocal<Executor>();

	static {
		setExecutor(new NonOperationalExecutor());
	}

	/**
	 * Returns the executor of the current thread.
	 *
	 * @return The executor of the current thread.
	 */
	public static Executor getExecutor() {
		return executor.get();
	}

	/**
	 * Sets the executor for the current thread.
	 *
	 * @param executor The executor for the current thread.
	 */
	public static void setExecutor(Executor executor) {
		ExecutorHolder.executor.set(executor);
	}
}
