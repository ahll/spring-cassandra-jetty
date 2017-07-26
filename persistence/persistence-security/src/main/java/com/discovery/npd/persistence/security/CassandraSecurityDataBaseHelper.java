/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.persistence.security;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.datastax.driver.extras.codecs.enums.EnumNameCodec;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.discovery.npd.domain.security.Permission;
import com.discovery.npd.persistence.CassandraConstants;

/**
 *
 * @author Lili
 */
public final class CassandraSecurityDataBaseHelper implements CassandraConstants {

    static final String DATA_BASE = "NpdSecurityDataBase";

    private final Session session;

    private CassandraSecurityDataBaseHelper(Session session) {
        this.session = session;
        createKeySpace();
    }

    public static CassandraSecurityDataBaseHelper getCassandraDataSourceHelper(String... ips) {

        return getCassandraDataSourceHelper(CASSANDRA_DEFAULT_PORT, ips);
    }

    public static CassandraSecurityDataBaseHelper getCassandraDataSourceHelper(Integer port, String... ips) {

        Cluster cluster = Cluster.builder()
                .withPort(port)
                .addContactPoints(ips)
                .build();
        cluster.getConfiguration().getCodecRegistry()
                .register(new EnumNameCodec<Permission>(Permission.class));
        return new CassandraSecurityDataBaseHelper(cluster.newSession());
    }

    public void createKeySpace() {
        session.execute("CREATE KEYSPACE IF NOT EXISTS " + DATA_BASE
                + " WITH replication " + "= {'class':'SimpleStrategy', 'replication_factor':3};");
        session.execute(CassandraCredentialTable.getTableScheme());
        session.execute(CassandraCredentialTable.getEmailIndexScheme());
        session.execute(CassandraTokenAccessor.getTableScheme());
    }

    public <T> Mapper<T> getDataMapper(Class<T> classType) {
        return new MappingManager(session).mapper(classType);
    }

    public <A> A createAccessor(Class<A> classType) {
        return new MappingManager(session).createAccessor(classType);
    }
}
