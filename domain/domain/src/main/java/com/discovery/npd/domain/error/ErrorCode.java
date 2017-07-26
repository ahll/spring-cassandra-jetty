/*
 * Copyright (c) 2017, discovery software and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.domain.error;

import java.util.function.Supplier;

/**
 * Error code class. Define global error code.
 *
 * @author Lili
 */
@FunctionalInterface
public interface ErrorCode extends Supplier<String> {

    /**
     * Invalid error code.
     */
    public final static ErrorCode INVALID_ID = () -> "ID_INVALID";
    public final static ErrorCode INVALID_TICKER = () -> "TICKER_INVALID";
    public final static ErrorCode INVALID_TOKEN = () -> "TOKEN_INVALID";
    public final static ErrorCode INVALID_CREDENTIAL = () -> "CREDENTIAL_INVALID";

    /**
     * Not found error code.
     */
    public final static ErrorCode NOT_FOUND_RELATION = () -> "RELATION_NOT_FOUND";
    public final static ErrorCode NOT_FOUND_CREDENTIAL = () -> "CREDENTIAL_NOT_FOUND";
    public final static ErrorCode NOT_FOUND_USER = () -> "USER_NOT_FOUND";
    public final static ErrorCode NOT_FOUND_STOCK = () -> "STOCK_NOT_FOUND";
    public final static ErrorCode NOT_FOUND_INDEX = () -> "INDEX_NOT_FOUND";
    public final static ErrorCode NOT_FOUND_MARKET = () -> "MARKET_NOT_FOUND";

    /**
     * Exist error code.
     */
    public final static ErrorCode EXIST_USER = () -> "USER_EXISTED";
    public final static ErrorCode EXIST_RELATION = () -> "RELATION_EXISTED";
    public final static ErrorCode EXIST_CREDENTIAL = () -> "CREDENTIAL_EXISTED";

    /**
     * Analytics Ranking
     */
    public final static ErrorCode DIFF_DATA_LENGTH = () -> "DATA_LENGTH_NOT_SAME";
}
