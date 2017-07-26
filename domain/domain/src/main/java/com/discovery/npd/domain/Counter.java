/*
 * Copyright (c) 2017, discovery software and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.domain;

/**
 *
 * Counter interface, manage counter increment.
 *
 * @author Lili
 */
public interface Counter {

    /**
     *
     * @return actual counter value
     */
    Long getValue();

    /**
     *
     * @return actual counter value by integer format
     */
    Integer getIntValue();

    /**
     * Increase counter, will renew database.
     */
    void increase();

    /**
     * Increase counter, will renew database.
     *
     * @param increasement the value want to increase
     */
    void increase(Integer increasement);

    /**
     * Decrease counter, will renew database.
     */
    void decrease();

    /**
     *
     * @param decreasement the value want to decrease
     */
    void decrease(Integer decreasement);

}
