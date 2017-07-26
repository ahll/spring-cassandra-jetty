/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.persistence;

import java.util.Date;

/**
 *
 * @author Lili
 * @param <K>
 * @param <V>
 */
public interface CassandraRelationKey<K, V> {

    public K fromId();

    public V toId();

    public Date creation();
}
