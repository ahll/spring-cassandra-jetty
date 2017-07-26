/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.api.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.nosql.appender.cassandra.CassandraAppender;

/**
 * A java Logger configuration class, to avoid text configuration.
 *
 * @author Lili
 */
public class LogFactory {

    private String loggerName = "Npd_log";
    private String appName = "Npd_log";
    private String loggerFilePath = "/npd/log/npd_log";
    private String cassandraKeyspace = "npd_log";
    private String cassandraHosts = "localhost";
    private Boolean toCassandra = false;
    private Boolean toFile = false;
    private Boolean toConsole = false;
    private Boolean basicLoggerConfig = false;

    public String getCassandraHosts() {
        return cassandraHosts;
    }

    public void setCassandraHosts(String cassandraHosts) {
        this.cassandraHosts = cassandraHosts;
    }

    public void setBasicLoggerConfig(Boolean basicLoggerConfig) {
        this.basicLoggerConfig = basicLoggerConfig;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getLoggerFilePath() {
        return loggerFilePath;
    }

    public void setLoggerFilePath(String loggerFilePath) {
        this.loggerFilePath = loggerFilePath;
    }

    public String getCassandraKeyspace() {
        return cassandraKeyspace;
    }

    public void setCassandraKeyspace(String cassandraKeyspace) {
        this.cassandraKeyspace = cassandraKeyspace;
    }

    public Boolean isToCassandra() {
        return toCassandra;
    }

    public void setToCassandra(Boolean toCassandra) {
        this.toCassandra = toCassandra;
    }

    public Boolean isToFile() {
        return toFile;
    }

    public void setToFile(Boolean toFile) {
        this.toFile = toFile;
    }

    public Boolean isToConsole() {
        return toConsole;
    }

    public void setToConsole(Boolean toPrint) {
        this.toConsole = toPrint;
    }

    /**
     * The out put pattern.
     */
    private final static String outputPattern
            = "%d{dd-MM-yyyy HH:mm:ss} %C %L %-5p: %m%n";

    public Logger getLogger() {
        return getLogger(Level.DEBUG);
    }

    public Logger getErrorLogger() {
        return getLogger(Level.ERROR);
    }

    public Logger getInfoLogger() {
        return getLogger(Level.INFO);
    }

    public Logger getDebugLogger() {
        return getLogger(Level.DEBUG);
    }

    public Logger getLogger(Level level) {
        LoggerContext ctx = (LoggerContext) LogManager.getContext();
        Configuration config = ctx.getConfiguration();
        LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
        loggerConfig.setLevel(level);
        ctx.updateLoggers();

        if (toCassandra) {

            CassandraAppender cassandraAppender = CassandraAppender
                    .newBuilder()
                    .build();
            config.getRootLogger().addAppender(cassandraAppender, level, null);

        }

        if (toFile) {

        }

        if (toConsole) {
            final PatternLayout layout = PatternLayout.createDefaultLayout(config);
            ConsoleAppender ca = ConsoleAppender.createDefaultAppenderForLayout(layout);
            ca.start();
            config.addAppender(ca);
            ctx.updateLoggers(config);
        }

        return ctx.getLogger(appName);
    }

}
