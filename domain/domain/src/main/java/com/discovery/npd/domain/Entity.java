
/*
 * Copyright (c) 2017, discovery software and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.domain;

/**
 * Entity class, model of entity domain, which identified by unique key.
 *
 * @author Lili
 * @param <KEY> the key of entity {@link  KEY}
 */
public interface Entity<KEY> {

    /**
     * Get entity key.
     *
     * @return key {@link KEY}
     */
    KEY getId();

}
