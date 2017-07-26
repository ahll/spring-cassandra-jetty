/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.persistence.security;

import com.datastax.driver.mapping.Result;
import com.datastax.driver.mapping.annotations.Accessor;
import com.datastax.driver.mapping.annotations.Param;
import com.datastax.driver.mapping.annotations.Query;
import static com.discovery.npd.persistence.security.CassandraSecurityDataBaseHelper.DATA_BASE;
import com.discovery.npd.persistence.CassandraConstants;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Lili
 */
@Accessor
public interface CassandraTokenAccessor extends CassandraConstants {

    public static final String TABLE_NAME = "token_table";
    public static final String ID_COLUMN = "id";
    public static final String PERMISSION_COLUMN = "permission";
    public static final String CREDENTIAL_ID_COLUMN = "credentialid";

    public static String getTableScheme() {
        return "CREATE TABLE IF NOT EXISTS "
                + DATA_BASE + "." + TABLE_NAME + " ("
                + "PRIMARY KEY (" + ID_COLUMN + "," + PERMISSION_COLUMN + ") ,"
                + ID_COLUMN + " " + CASSANDRA_VALUE_TYPE_UUID + ","
                + PERMISSION_COLUMN + " " + CASSANDRA_VALUE_TYPE_STRING + ","
                + CREDENTIAL_ID_COLUMN + " " + CASSANDRA_VALUE_TYPE_UUID
                + ");";
    }

    @Query("Select * from " + DATA_BASE + "." + TABLE_NAME + " where " + ID_COLUMN + "= :id")
    Result<CassandraToken> getTokenResult(@Param("id") UUID id);

    default List<CassandraToken> getTokenList(UUID id) {
        return getTokenResult(id).all();
    }

}
