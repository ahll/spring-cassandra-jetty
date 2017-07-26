/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.persistence;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.mapping.Mapper;
import com.discovery.npd.domain.Entity;
import com.discovery.npd.domain.EntityRepository;
import java.util.List;

/**
 *
 * @author Lili
 * @param <KEY> key of entity
 * @param <ENTITY> entity
 * @param <DATA> Cassandra persistence object
 */
public abstract class CassandraEntityRepository<KEY, ENTITY extends Entity<KEY>, DATA extends ENTITY>
        implements EntityRepository<KEY, ENTITY> {

    protected Mapper<DATA> mapper;

    protected Session session;

    protected abstract DATA parse(ENTITY entity);

    protected String tableName;

    protected String idColumn;

    protected String keySpace;

    @Override
    public void save(ENTITY entity) {
        mapper.save(parse(entity));
    }

    @Override
    public ENTITY one(KEY key) {
        return mapper.get(key);
    }

    @Override
    public List<? extends ENTITY> in(List<KEY> keys) {

        ResultSet result = session.execute(
                QueryBuilder
                        .select()
                        .from(keySpace, tableName)
                        .where(QueryBuilder.in(idColumn, keys))
        );

        return mapper.map(result).all();
    }

    @Override
    public List<? extends ENTITY> all() {
        ResultSet result = session.execute(
                QueryBuilder
                        .select()
                        .from(keySpace, tableName)
        );

        return mapper.map(result).all();
    }

    public ENTITY find(
            String keyName,
            Object keyValue
    ) {
        ResultSet result = session.execute(
                QueryBuilder
                        .select()
                        .from(keySpace, tableName)
                        .where(QueryBuilder.eq(keyName, keyValue))
        );

        return mapper.map(result).one();
    }

}
