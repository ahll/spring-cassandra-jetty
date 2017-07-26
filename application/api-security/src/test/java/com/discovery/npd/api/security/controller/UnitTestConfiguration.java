/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.api.security.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.discovery.npd.api.handling.ApiAuthenticator;
import com.discovery.npd.api.handling.ApiLogger;
import java.io.IOException;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author Lili
 */
public interface UnitTestConfiguration {

    @Bean
    default ApiAuthenticator apiAuthenticator() throws IOException {
        return new ApiAuthenticator() {
            @Override
            protected Boolean verify(String text) {
                return Boolean.TRUE;
            }
        };
    }

    @Bean
    default ObjectMapper getObjectMapper() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        return mapper;
    }

    @Bean
    default Logger getLogger() throws IOException {
        return ApiLogger.LOGGER;
    }

}
