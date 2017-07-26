/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.api.handling;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Lili
 */
public interface RequestHeader {

    public static final String ACCESSTOKEN_HEADER_NAME = "accessToken";
    public static final String API_VERSION = "version";

    public static String extractToken(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader(ACCESSTOKEN_HEADER_NAME);
    }

    public static String extractVersion(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader(API_VERSION);
    }

}
