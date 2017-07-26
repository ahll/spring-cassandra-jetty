
/*
 * Copyright (c) 2017, discovery software and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.domain;

/**
 * Index class, domain of index model, which is identified by key{@link KEY} and
 * index {@link INDEX} .
 *
 * @author Lili
 * @param <KEY> the index key {@link  KEY}
 * @param <INDEX> the index value {@link INDEX}
 */
public interface Index<KEY, INDEX> extends Entity<KEY> {

    /**
     * Get index value {@link INDEX}.
     *
     * @return index value {@link INDEX}
     */
    INDEX getIndex();
}
