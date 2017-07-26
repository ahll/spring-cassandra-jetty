/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.persistence;

import com.discovery.npd.domain.Entity;
import com.discovery.npd.domain.Relation;
import java.util.Date;

/**
 *
 * @author Lili
 * @param <FROM>
 * @param <TO>
 */
public class CassandraRelation<FROM extends Entity, TO extends Entity> implements Relation<FROM, TO> {

    private FROM from;
    private TO to;
    private Date creation;

    @Override
    public FROM getFrom() {
        return from;
    }

    public CassandraRelation<FROM, TO> from(FROM from) {
        this.from = from;
        return this;
    }

    @Override
    public TO getTo() {
        return to;
    }

    public CassandraRelation<FROM, TO> to(TO to) {
        this.to = to;
        return this;
    }

    @Override
    public Date getCreation() {
        return creation;
    }

    public CassandraRelation<FROM, TO> creation(Date creation) {
        this.creation = creation;
        return this;
    }

}
