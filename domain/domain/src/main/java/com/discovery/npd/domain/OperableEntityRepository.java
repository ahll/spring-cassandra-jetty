
/*
 * Copyright (c) 2017, discovery software and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.domain;

import com.discovery.npd.domain.error.Nulls;
import java.util.UUID;

/**
 *
 * Repository to manager operable entity.
 *
 * @author Lili
 * @param <ENTITY> entity {@link ENTITY}
 */
public interface OperableEntityRepository<ENTITY extends OperableEntity>
        extends EntityRepository<UUID, ENTITY> {

    /**
     * Create a new entity with new key.
     *
     * @param entity {@link ENTITY} entity to create, which without key.
     * @return entity {@link ENTITY} created.
     */
    default ENTITY create(ENTITY entity) {

        /**
         * To find a new id.
         */
        UUID id = UUID.randomUUID();
        while (Nulls.notNull(one(id))) {
            id = UUID.randomUUID();
        }
        entity.setId(id);

        /**
         * Save entity with new id.
         */
        save(entity);
        return entity;
    }

}
