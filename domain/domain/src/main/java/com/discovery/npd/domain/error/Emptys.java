/*
 * Copyright (c) 2017, discovery software and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.domain.error;

import java.util.Collection;
import java.util.Objects;

/**
 * Empty assistance class.
 *
 * @author Lili
 */
public interface Emptys {

    /**
     * Require non empty.
     *
     * @param <T> {@link T}
     * @param object object to verify
     * @param errorCode
     * @throws com.discovery.npd.domain.error.ErrorException
     */
    public static <T extends Collection> void requireNonEmpty(T object, ErrorCode errorCode) throws ErrorException {

        if (Objects.isNull(object) || object.isEmpty()) {
            throw new ErrorException(errorCode);
        }
    }

    /**
     * Require non empty.
     *
     * @param text object to verify
     * @param errorCode
     * @throws com.discovery.npd.domain.error.ErrorException
     */
    public static void requireNonEmpty(String text, ErrorCode errorCode) throws ErrorException {

        if (Objects.isNull(text) || text.isEmpty()) {
            throw new ErrorException(errorCode);
        }
    }

    /**
     * Require non empty.
     *
     * @param text object to verify
     * @return if is null or empty
     * @throws com.discovery.npd.domain.error.ErrorException
     */
    public static Boolean isNullOrEmpty(String text) {
        return Objects.isNull(text) || text.isEmpty();

    }

    /**
     * Require not null but empty.
     *
     * @param <T> {@link T}
     * @param object object to verify
     * @param errorCode
     */
    public static <T extends Collection> void requireNonNullButEmpty(T object, ErrorCode errorCode)
            throws ErrorException {

        if (Objects.isNull(object) || !object.isEmpty()) {
            throw new ErrorException(errorCode);
        }
    }

}
