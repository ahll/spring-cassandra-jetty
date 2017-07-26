/*
 * Copyright (c) 2017, discovery software and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.domain.error;

/**
 *
 * Error code based exception class.
 *
 * @author Lili
 */
public class ErrorException extends RuntimeException {

    /**
     * Error code, which caused exception.
     */
    private final ErrorCode error;

    public ErrorException(ErrorCode error) {
        super(error.get());
        this.error = error;
    }

    public ErrorCode getError() {
        return error;
    }

}
