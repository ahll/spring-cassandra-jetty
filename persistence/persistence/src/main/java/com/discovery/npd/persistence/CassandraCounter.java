/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.persistence;

import com.discovery.npd.domain.Counter;

/**
 *
 * @author Lili
 * @param <KEY>
 */
public class CassandraCounter<KEY> implements Counter {

    private final String counterName;
    private final KEY id;
    private final Long value;
    private final CassandraCounterRepository<KEY> repository;

    CassandraCounter(
            KEY id,
            String counterName,
            Long value,
            CassandraCounterRepository<KEY> repository
    ) {
        this.id = id;
        this.counterName = counterName;
        this.value = value;
        this.repository = repository;
    }

    String getCounterName() {
        return counterName;
    }

    KEY getId() {
        return id;
    }

    @Override
    public Long getValue() {
        return value;
    }

    @Override
    public Integer getIntValue() {
        return value.intValue();
    }

    @Override
    public void increase() {
        repository.increase(this);
    }

    @Override
    public void increase(Integer increasement) {
        repository.increase(this, increasement);
    }

    @Override
    public void decrease() {
        repository.decrease(this);
    }

    @Override
    public void decrease(Integer decreasement) {
        repository.decrease(this, decreasement);
    }

}
