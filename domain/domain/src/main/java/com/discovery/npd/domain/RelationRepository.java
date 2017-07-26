
/*
 * Copyright (c) 2017, discovery software and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.domain;

import com.discovery.npd.domain.error.ErrorException;
import java.util.List;

/**
 * A domain repository to manager the relation, which is defined as "TO-->FROM".
 *
 * @author Lili
 * @param <FROMKEY> key of from entity {@link FROMKEY}
 * @param <TOKEY> key of to entity {@link TOKEY}
 * @param <FROM> from entity {@link FROM}, which is the starting point of
 * relation.
 * @param <TO> to entity {@link TO}, which is the ending point of relation.
 */
public interface RelationRepository<FROMKEY, TOKEY, FROM extends Entity<FROMKEY>, TO extends Entity<TOKEY>> {

    /**
     * Save a relation by his keys.
     *
     * @param fromKey key of from {@link FROMKEY}
     * @param toKey key of to {@link TOKEY}
     */
    void create(FROMKEY fromKey, TOKEY toKey) throws ErrorException;
    
    /**
     * Delete a relation by his keys.
     *
     * @param fromKey key of from {@link FROMKEY}
     * @param toKey key of to {@link TOKEY}
     */
    void delete(FROMKEY fromKey, TOKEY toKey) throws ErrorException;

    /**
     * Give the relation by from key and to key. Null{@code null} will be
     * returned if not existing the relation.
     *
     * @param fromKey key of from
     * @param toKey key of to
     * @return the relation with from key and to key
     */
    Relation< FROM, TO> one(FROMKEY fromKey, TOKEY toKey) throws ErrorException;

    /**
     *
     * Give all relations which their from entity's key is the given from key.
     *
     * @param fromKey key of from
     * @return all relations {@link Relation} associated with from key
     */
    List<? extends Relation< FROM, TO>> all(FROMKEY fromKey) throws ErrorException;

    /**
     * Give all to entities that are ending point of relations as their starting
     * point key is given from key.
     *
     * @param fromKey key of from
     * @return all ending points of relationship with starting point as from key
     */
    List<? extends TO> allTo(FROMKEY fromKey) throws ErrorException;

}
