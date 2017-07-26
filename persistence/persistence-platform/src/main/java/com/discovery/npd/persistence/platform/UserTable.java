/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.persistence.platform;

import com.datastax.driver.mapping.annotations.Accessor;
import static com.discovery.npd.persistence.CassandraConstants.CASSANDRA_VALUE_TYPE_STRING;
import static com.discovery.npd.persistence.CassandraConstants.CASSANDRA_VALUE_TYPE_UUID;
import static com.discovery.npd.persistence.platform.PlatformDataBaseHelper.DATA_BASE;

/**
 *
 * @author Lili
 * @author daniel.eguia
 */
@Accessor
public interface UserTable {

    public static final String TABLE_NAME = "user";
    public static final String ID_COLUMN = "id";
    public static final String NICKNAME_COLUMN = "nickname";
    public static final String LOCATION_COLUMN = "location";

    public static String getTableScheme() {
        return "CREATE TABLE IF NOT EXISTS "
                + DATA_BASE + "." + TABLE_NAME + " ("
                + "PRIMARY KEY (" + ID_COLUMN + ") ,"
                + ID_COLUMN + " " + CASSANDRA_VALUE_TYPE_UUID + ","
                + NICKNAME_COLUMN + " " + CASSANDRA_VALUE_TYPE_STRING
                + ");";
    }

}
