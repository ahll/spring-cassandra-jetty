/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.persistence.security;

import com.datastax.driver.mapping.annotations.Accessor;
import static com.discovery.npd.persistence.CassandraConstants.*;
import static com.discovery.npd.persistence.security.CassandraSecurityDataBaseHelper.DATA_BASE;

/**
 *
 * @author Lili
 */
@Accessor
public interface CassandraCredentialTable {

    public static final String TABLE_NAME = "credential";
    public static final String ID_COLUMN = "id";
    public static final String EMAIL_COLUMN = "email";
    public static final String PASSWORD_COLUMN = "password";
    public static final String PERMISSION_COLUMN = "permissionList";

    public static String getTableScheme() {
        return "CREATE TABLE IF NOT EXISTS "
                + DATA_BASE + "." + TABLE_NAME + " ("
                + "PRIMARY KEY (" + ID_COLUMN + ") ,"
                + ID_COLUMN + " " + CASSANDRA_VALUE_TYPE_UUID + ","
                + EMAIL_COLUMN + " " + CASSANDRA_VALUE_TYPE_STRING + ","
                + PERMISSION_COLUMN + " " + CASSANDRA_VALUE_TYPE_LIST_STRING + ","
                + PASSWORD_COLUMN + " " + CASSANDRA_VALUE_TYPE_STRING
                + ");";
    }

    public static String getEmailIndexScheme() {
        return "CREATE INDEX IF NOT EXISTS ON " + DATA_BASE + "." + TABLE_NAME
                + "(" + EMAIL_COLUMN + " )";
    }

}
