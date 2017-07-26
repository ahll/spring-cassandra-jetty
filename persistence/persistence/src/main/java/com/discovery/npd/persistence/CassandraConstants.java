/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.persistence;

/**
 *
 * @author Lili
 */
public interface CassandraConstants {

    public final static Integer CASSANDRA_DEFAULT_PORT = 9042;
    public static final String TICKER_COLUMN = "ticker";
    public final static String CASSANDRA_VALUE_TYPE_LONG = "bigint";
    public final static String CASSANDRA_VALUE_TYPE_STRING = "text";
    public final static String CASSANDRA_VALUE_TYPE_LIST_STRING = "list<text>";
    public final static String CASSANDRA_VALUE_TYPE_DOUBLE = "double";
    public final static String CASSANDRA_VALUE_TYPE_FLOAT = "float";
    public final static String CASSANDRA_VALUE_TYPE_UUID = "uuid";
    public final static String CASSANDRA_VALUE_TYPE_DATE = "timestamp";
    public static final String LIMIT = "limit";
    public static final String SIZE = "size";
    public static final String CREATION_TIME_COLUMN = "creationtime";

}
