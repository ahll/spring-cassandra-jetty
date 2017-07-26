/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.api.logging;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

/**
 * Class representing an API log entry.
 *
 * @version 1.0
 */
public class LogEntry {

    public static final int MAX_LINE_LENGTH = 10000;
    private HttpServletRequest httpServletRequest;
    private long start;
    private StringBuilder message;
    private Exception exception;
    private Logger logger;

    /**
     * Constructor.
     *
     * @param httpServletRequest the logged {@link HttpServletRequest}
     * @param loggerName the logger name
     */
    public LogEntry(HttpServletRequest httpServletRequest, Logger logger) {
        this.start = System.currentTimeMillis();
        this.message = new StringBuilder();
        this.httpServletRequest = httpServletRequest;
        this.logger = logger;
    }

    /**
     * Appends the specified message to this log entry
     *
     * @param message
     */
    public void append(String message) {

        // Truncate message
        String truncated;
        if (message.length() > MAX_LINE_LENGTH) {
            truncated = message.substring(0, MAX_LINE_LENGTH - 1);
        } else {
            truncated = message;
        }

        // Append formatted message
        this.message.append("        ");
        this.message.append(truncated);
        this.message.append('\n');
    }

    /**
     * Sets the {@link Exception} to be logged
     *
     * @param exception the exception to set
     */
    public void setException(Exception exception) {
        exception.printStackTrace();
        this.exception = exception;
    }

    /**
     * Writes to logger.
     *
     */
    public void log() {

        Level level = exception == null ? Level.DEBUG : Level.ERROR;

        long finish = System.currentTimeMillis();
        long time = finish - start;
        append("SERVER TIME   : " + time + "ms");

        String ip = "[" + httpServletRequest.getRemoteAddr() + "]";
        String path = "[" + httpServletRequest.getRequestURI() + "]";
        String message = ip + " " + path + "\n" + this.message;
        logger.log(level, message, exception);
    }

}
