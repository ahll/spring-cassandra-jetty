/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.persistence;

import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import static com.datastax.driver.core.querybuilder.QueryBuilder.decr;
import static com.datastax.driver.core.querybuilder.QueryBuilder.incr;

/**
 *
 * @author Lili
 * @param <KEY> counter simple key column type
 */
public class CassandraCounterRepository<KEY> {

    protected String tableName;

    protected String idColumn;

    protected String keySpace;

    protected Session session;

    protected CassandraCounter newCounter(String name, Long value, KEY key) {
        CassandraCounter<KEY> counter = new CassandraCounter<>(
                key, name, value, this
        );
        return counter;
    }

    void increase(CassandraCounter counter, Integer value) {
        session.execute(
                QueryBuilder
                        .update(keySpace, tableName)
                        .with(incr(counter.getCounterName(), value))
                        .where(QueryBuilder.eq(idColumn, counter.getId()))
        );

    }

    void decrease(CassandraCounter counter, Integer value) {
        session.execute(
                QueryBuilder
                        .update(keySpace, tableName)
                        .with(decr(counter.getCounterName(), value))
                        .where(QueryBuilder.eq(idColumn, counter.getId()))
        );
    }

    void increase(CassandraCounter counter) {
        increase(counter, 1);
    }

    void decrease(CassandraCounter counter) {
        decrease(counter, 1);
    }

}
