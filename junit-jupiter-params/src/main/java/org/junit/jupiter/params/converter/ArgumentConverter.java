/*
 * Copyright 2015-2025 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package org.junit.jupiter.params.converter;

import static org.apiguardian.api.API.Status.EXPERIMENTAL;
import static org.apiguardian.api.API.Status.STABLE;

import org.apiguardian.api.API;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.params.support.FieldContext;
import org.junit.platform.commons.JUnitException;

/**
 * {@code ArgumentConverter} is an abstraction that allows an input object to
 * be converted to an instance of a different class.
 *
 * <p>Such an {@code ArgumentConverter} is applied to the method parameter
 * of a {@link org.junit.jupiter.params.ParameterizedTest @ParameterizedTest}
 * or a constructor parameter or
 * {@link org.junit.jupiter.params.Parameter @Parameter}-annotated field of a
 * {@link org.junit.jupiter.params.ParameterizedClass @ParameterizedClass} with
 * the help of a
 * {@link org.junit.jupiter.params.converter.ConvertWith @ConvertWith}
 * annotation.
 *
 * <p>Implementations must provide a no-args constructor or a single unambiguous
 * constructor to use {@linkplain ParameterResolver parameter resolution}. They
 * should not make any assumptions regarding when they are instantiated or how
 * often they are called. Since instances may potentially be cached and called
 * from different threads, they should be thread-safe.
 *
 * <p>Extend {@link SimpleArgumentConverter} if your implementation only needs
 * to know the target type and does not need access to the {@link ParameterContext}
 * to perform the conversion.
 *
 * <p>Extend {@link TypedArgumentConverter} if your implementation always converts
 * from a given source type into a given target type and does not need access to
 * the {@link ParameterContext} to perform the conversion.
 *
 * @since 5.0
 * @see org.junit.jupiter.params.ParameterizedTest
 * @see org.junit.jupiter.params.converter.ConvertWith
 * @see org.junit.jupiter.params.support.AnnotationConsumer
 * @see SimpleArgumentConverter
 * @see TypedArgumentConverter
 */
@API(status = STABLE, since = "5.7")
public interface ArgumentConverter {

	/**
	 * Convert the supplied {@code source} object according to the supplied
	 * {@code context}.
	 *
	 * @param source the source object to convert; may be {@code null}
	 * @param context the parameter context where the converted object will be
	 * supplied; never {@code null}
	 * @return the converted object; may be {@code null} but only if the target
	 * type is a reference type
	 * @throws ArgumentConversionException if an error occurs during the
	 * conversion
	 */
	@Nullable
	Object convert(@Nullable Object source, ParameterContext context) throws ArgumentConversionException;

	/**
	 * Convert the supplied {@code source} object according to the supplied
	 * {@code context}.
	 *
	 * @param source the source object to convert; may be {@code null}
	 * @param context the field context where the converted object will be
	 * injected; never {@code null}
	 * @return the converted object; may be {@code null} but only if the target
	 * type is a reference type
	 * @throws ArgumentConversionException if an error occurs during the
	 * conversion
	 * @since 5.13
	 */
	@API(status = EXPERIMENTAL, since = "6.0")
	default @Nullable Object convert(@Nullable Object source, FieldContext context) throws ArgumentConversionException {
		throw new JUnitException("""
				ArgumentConverter does not override the convert(Object, FieldContext) method. \
				Please report this issue to the maintainers of %s.""".formatted(getClass().getName()));
	}
}
