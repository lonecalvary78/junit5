/*
 * Copyright 2015-2025 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package org.junit.jupiter.api;

import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;

/**
 * @since 5.8
 */
@DisplayNameGeneration(ReplaceUnderscores.class)
class DisplayNameGenerationInheritanceTestCase {

	@Nested
	class InnerNestedTestCase {

		@Test
		void this_is_a_test() {
		}
	}

	@SuppressWarnings("JUnitMalformedDeclaration")
	static class StaticNestedTestCase {

		@Test
		void this_is_a_test() {
		}
	}

}
