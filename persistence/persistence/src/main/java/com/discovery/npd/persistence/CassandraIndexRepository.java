/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.persistence;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.discovery.npd.domain.Index;
import com.discovery.npd.domain.IndexRepository;
import java.util.List;

/**
 *
 * @author Lili
 * @param <KEY> key of entity
 * @param <INDEX>
 * @param <ENTITY> entity
 * @param <DATA> Cassandra persistence object
 */
public abstract class CassandraIndexRepository<KEY, INDEX, ENTITY extends Index<KEY, INDEX>, DATA extends ENTITY>
        extends CassandraEntityRepository<KEY, ENTITY, DATA>
        implements IndexRepository<KEY, INDEX, ENTITY> {

    protected String indexColumn;

    @Override
    public List<? extends ENTITY> getDescendantListBelow(KEY key, INDEX index, Integer limit) {
        ResultSet result = session.execute(
                QueryBuilder
                        .select()
                        .from(keySpace, tableName)
                        .where(QueryBuilder.eq(idColumn, key))
                        .and(QueryBuilder.lt(indexColumn, index))
                        .orderBy(QueryBuilder.desc(indexColumn))
                        .limit(limit)
        );
        return mapper.map(result).all();
    }

    @Override
    public List<? extends ENTITY> getAscendantListBetween(KEY key, INDEX starting, INDEX ending) {
        ResultSet result = session.execute(
                QueryBuilder
                        .select()
                        .from(keySpace, tableName)
                        .where(QueryBuilder.eq(idColumn, key))
                        .and(QueryBuilder.lte(indexColumn, ending))
                        .and(QueryBuilder.gte(indexColumn, starting))
        );
        return mapper.map(result).all();
    }

}
