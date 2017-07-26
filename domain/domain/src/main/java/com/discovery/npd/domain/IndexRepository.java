
/*
 * Copyright (c) 2017, discovery software and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.domain;

import java.util.List;

/**
 * A domain repository to manager indexes.
 *
 * @author Lili
 * @param <KEY> index key {@link KEY}
 * @param <INDEX> index value {@link  INDEX}
 * @param <ENTITY> index entity {@link ENTITY}
 */
public interface IndexRepository<KEY, INDEX, ENTITY extends Index<KEY, INDEX>>
        extends EntityRepository<KEY, ENTITY> {

    /**
     * This method will pick those most nearly data that below the index value
     * parameter, and return them in descendant order.
     *
     * @param key key {@link KEY}
     * @param index index value {@link INDEX}
     * @param limit data limit
     * @return list of data in descendant order.
     */
    List<? extends ENTITY> getDescendantListBelow(
            KEY key,
            INDEX index,
            Integer limit
    );

    /**
     * This method will pick those data that between the starting and the ending
     * index value, then return them in ascendant order.
     *
     * @param key key {@link KEY}
     * @param starting index value {@link INDEX}
     * @param ending data limit {@link INDEX}
     * @return list of data in descendant order.
     */
    List<? extends ENTITY> getAscendantListBetween(
            KEY key,
            INDEX starting,
            INDEX ending
    );

}
