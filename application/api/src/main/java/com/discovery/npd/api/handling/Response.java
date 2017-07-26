/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.api.handling;

import com.discovery.npd.domain.error.ErrorException;
import java.util.List;
import java.util.function.Supplier;

/**
 *
 * @author Lili
 */
public interface Response {

    public static Response resolve(Supplier<Response> supplier) {
        try {
            return supplier.get();
        } catch (ErrorException errorException) {
            return ApiResponse.error(errorException.getError());
        }
    }

    enum Status {
        REQUEST_ERROR, OK, SERVER_ERROR
    }

    Status getStatus();

    <RESPONSEDATA> RESPONSEDATA getResponseData();

    List<String> getErrorList();

}
