
/*
 * Copyright (c) 2017, discovery software and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.domain;

import java.util.Date;

/**
 *
 * Relation class, model of relation domain, which is identified by two
 * entities.
 *
 * @author Lili
 * @param <FROM> {@link FROM} the starting point of the relationship.
 * @param <TO> {@link TO} the ending point of the relationship.
 */
public interface Relation< FROM extends Entity, TO extends Entity> {

    /**
     * Give the starting point of the relation.
     *
     * @return from {@link FROM}
     */
    FROM getFrom();

    /**
     * Give the ending point of the relation.
     *
     * @return to {@link TO}
     */
    TO getTo();

    /**
     * Give the relationship creation time.
     *
     * @return creation time {@link Date}
     */
    Date getCreation();

}
