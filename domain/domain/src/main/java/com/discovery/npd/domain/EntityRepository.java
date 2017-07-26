
/*
 * Copyright (c) 2017, discovery software and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.domain;

import java.util.List;

/**
 * The repository to manager entities.
 *
 * @author Lili
 * @param <KEY> entity key {@link KEY}
 * @param <ENTITY> entity {@link ENTITY}
 */
public interface EntityRepository<KEY, ENTITY extends Entity<KEY>> {

    /**
     * Save a entity, using for create and update.
     *
     * @param entity {@link ENTITY}
     */
    void save(ENTITY entity);

    /**
     * Give all exist entities.
     *
     * @return all entity {@link ENTITY} as list.
     */
    List<? extends ENTITY> all();

    /**
     *
     * Give one entity by key, {@code null} will be returned if not existing
     * entity with this key.
     *
     * @param key {@link  KEY}
     * @return entity by key.
     */
    ENTITY one(KEY key);

    /**
     * Give entities which their key are in the key list.
     *
     * @param keys key {@link KEY} list
     * @return entity {@link ENTITY} list
     */
    List<? extends ENTITY> in(List<KEY> keys);

}
