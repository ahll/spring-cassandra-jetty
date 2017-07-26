/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.api.handling;

import com.discovery.npd.api.logging.LogEntry;
import com.discovery.npd.api.logging.LogFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Logger;

/**
 * A java Logger configuration class.
 *
 * @author Lili
 */
public class ApiLogger {

    /**
     * Simple logger.
     */
    public static final Logger LOGGER;

    private static final ObjectMapper MAPPER;

    public ApiLogger() {
    }

    static {
        MAPPER = new ObjectMapper();
        /**
         * Get logger instance.
         */
        LogFactory logFactory = new LogFactory() {
        };
        logFactory.setAppName("Npd_Api");
        logFactory.setLoggerName("Npd_Api");
        logFactory.setToCassandra(false);
        logFactory.setToConsole(true);
        logFactory.setBasicLoggerConfig(true);
        logFactory.setToFile(false);
        LOGGER = logFactory.getLogger();
    }

    public static void log(HttpServletRequest httpServletRequest, Object request, Object response)
            throws IOException {

        String path = "[" + httpServletRequest.getRequestURI() + "]";
        ApiLogger.LOGGER.info(path);

        // We create the log4j entry
        LogEntry logEntry = new LogEntry(httpServletRequest, ApiLogger.LOGGER);

        // Get headers
        String accessToken = RequestHeader.extractToken(httpServletRequest);
        String apiVersion = RequestHeader.extractVersion(httpServletRequest);

        // Log headers
        logEntry.append("USER_ACCESS_TOKEN   : " + accessToken);
        logEntry.append("API_VERSION   : " + apiVersion);

        // Log body
        logEntry.append("REQUEST BODY  : " + MAPPER.writeValueAsString(request));

        // Log response body
        logEntry.append("RESPONSE BODY : " + MAPPER.writeValueAsString(response));

        // Write log entry
        logEntry.log();
    }
}
