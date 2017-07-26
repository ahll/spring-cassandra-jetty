/*
 * Copyright (c) 2017, discovery software and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.domain.error;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Null assistance class.
 *
 * @author Lili
 */
public interface Nulls {

    /**
     * Resolve value, avoiding null check.
     *
     * @param <T> {@link T> value to resolve
     * @param resolver resolver {@link Supplier}
     * @return {@link Optional} value in optional.
     */
    public static <T> Optional<T> resolve(Supplier<T> resolver) {
        try {
            /**
             * Get value.
             */
            T result = resolver.get();
            return Optional.ofNullable(result);
        } catch (NullPointerException e) {
            /**
             * Return empty.
             */
            return Optional.empty();
        }
    }

    /**
     *
     * @param <T> {@link T}
     * @param object object to verify
     * @return if object is null
     */
    public static <T> Boolean notNull(T object) {
        return Objects.nonNull(object);
    }

    /**
     *
     * @param <T> {@link T}
     * @param object object to verify
     * @param errorCode
     * @throws com.discovery.npd.domain.error.ErrorException
     */
    public static <T> void requireNonNull(T object, ErrorCode errorCode) throws ErrorException {
        if (Objects.isNull(object)) {
            throw new ErrorException(errorCode);
        }
    }

    /**
     *
     * @param <T> {@link T}
     * @param object object to verify
     */
    public static <T> void requireNull(T object, ErrorCode errorCode) throws ErrorException {
        if (!Objects.isNull(object)) {
            throw new ErrorException(errorCode);
        }
    }

}
