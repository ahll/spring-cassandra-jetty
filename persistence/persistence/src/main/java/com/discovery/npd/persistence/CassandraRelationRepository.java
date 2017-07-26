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
import com.discovery.npd.domain.Relation;
import com.discovery.npd.domain.RelationRepository;
import com.discovery.npd.domain.error.ErrorCode;
import com.discovery.npd.domain.error.ErrorException;
import com.discovery.npd.domain.error.Nulls;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author Lili
 * @param <FROMKEY>
 * @param <TOKEY>
 * @param <FROM>
 * @param <TO>
 * @param <KEY>
 */
public abstract class CassandraRelationRepository<FROMKEY, TOKEY, FROM extends Entity<FROMKEY>, TO extends Entity<TOKEY>, KEY extends CassandraRelationKey<FROMKEY, TOKEY>>
        implements RelationRepository<FROMKEY, TOKEY, FROM, TO> {

    protected EntityRepository<FROMKEY, FROM> fromRepository;
    protected EntityRepository<TOKEY, TO> toRepository;
    protected Mapper<KEY> relationKeyMapper;
    protected Session session;
    protected String tableName;
    protected String fromKeyColumn;
    protected String keySpace;
    protected ErrorCode fromError;
    protected ErrorCode toError;

    abstract protected KEY newKey(FROMKEY fromKey, TOKEY toKey);

    private FROM checkFrom(FROMKEY fromKey) throws ErrorException {
        FROM from = fromRepository.one(fromKey);
        Nulls.requireNonNull(from, fromError);
        return from;
    }

    private TO checkTo(TOKEY toKey) throws ErrorException {
        TO to = toRepository.one(toKey);
        Nulls.requireNonNull(to, toError);
        return to;
    }

    private void checkKey(FROMKEY fromKey, TOKEY toKey) throws ErrorException {
        KEY key = relationKeyMapper.get(fromKey, toKey);
        Nulls.requireNull(key, ErrorCode.EXIST_RELATION);
    }

    private KEY requireKey(FROMKEY fromKey, TOKEY toKey) throws ErrorException {
        KEY key = relationKeyMapper.get(fromKey, toKey);
        Nulls.requireNonNull(key, ErrorCode.NOT_FOUND_RELATION);
        return key;
    }

    @Override
    public void create(FROMKEY fromKey, TOKEY toKey) throws ErrorException {

        checkFrom(fromKey);

        checkTo(toKey);

        checkKey(fromKey, toKey);

        relationKeyMapper.save(newKey(fromKey, toKey));

    }
    
    @Override
    public void delete(FROMKEY fromKey, TOKEY toKey) throws ErrorException {

        checkFrom(fromKey);

        checkTo(toKey);

        requireKey(fromKey, toKey);

        relationKeyMapper.delete(newKey(fromKey, toKey));

    }

    @Override
    public Relation< FROM, TO> one(FROMKEY fromKey, TOKEY toKey) throws ErrorException {

        FROM from = checkFrom(fromKey);
        TO to = checkTo(toKey);
        KEY key = requireKey(fromKey, toKey);

        CassandraRelation<FROM, TO> relation = new CassandraRelation<>();

        relation.from(from)
                .to(to)
                .creation(key.creation());

        return relation;
    }

    @Override
    public List<? extends Relation< FROM, TO>> all(FROMKEY fromKey) throws ErrorException {

        FROM from = checkFrom(fromKey);

        ResultSet keyResilt = session.execute(
                QueryBuilder
                        .select()
                        .from(keySpace, tableName)
                        .where(QueryBuilder.eq(fromKeyColumn, fromKey))
        );

        List<KEY> keyList = relationKeyMapper.map(keyResilt).all();

        Map<TOKEY, Date> dateMap = keyList
                .stream()
                .collect(Collectors.toMap(
                        key -> key.toId(),
                        key -> key.creation()));

        List<CassandraRelation<FROM, TO>> result = toRepository
                .in(new ArrayList<>(dateMap.keySet()))
                .stream()
                .map(to -> {
                    CassandraRelation<FROM, TO> relation
                            = new CassandraRelation()
                                    .from(from)
                                    .to(to)
                                    .creation(dateMap.get(to.getId()));
                    return relation;
                })
                .collect(Collectors.toList());

        return result;
    }

    @Override
    public List<? extends TO> allTo(FROMKEY fromKey) throws ErrorException {

        checkFrom(fromKey);

        ResultSet keyResilt = session.execute(
                QueryBuilder
                        .select()
                        .from(keySpace, tableName)
                        .where(QueryBuilder.eq(fromKeyColumn, fromKey))
        );

        /**
         * Get relationship ending point key list.
         */
        List<TOKEY> keyList = relationKeyMapper
                .map(keyResilt)
                .all()
                .stream()
                .map(key -> key.toId())
                .collect(Collectors.toList());

        return toRepository.in(keyList);

    }

}
