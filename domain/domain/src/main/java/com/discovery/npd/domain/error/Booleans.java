
/*
 * Copyright (c) 2017, discovery software and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.domain.error;

/**
 * Boolean assistance class.
 *
 * @author Lili
 */
public interface Booleans {

    /**
     *
     * Throw error {@link ErrorException} in case require is not true.
     *
     * @param require {@link Boolean} boolean value.
     * @param errorCode error code{@link ErrorCode}
     * @throws com.discovery.npd.domain.error.ErrorException when require in not
     * true.
     */
    public static void requireTrue(Boolean require, ErrorCode errorCode)
            throws ErrorException {
        if (!require) {
            throw new ErrorException(errorCode);
        }
    }

    /**
     * Throw error {@link ErrorException} in case require is not false.
     *
     * @param require {@link Boolean} boolean value.
     * @param errorCode error code{@link ErrorCode}
     * @throws com.discovery.npd.domain.error.ErrorException when require in not
     * false.
     */
    public static void requireFalse(Boolean require, ErrorCode errorCode)
            throws ErrorException {
        if (require) {
            throw new ErrorException(errorCode);
        }
    }

}
