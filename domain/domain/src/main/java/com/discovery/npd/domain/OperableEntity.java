
/*
 * Copyright (c) 2017, discovery software and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.domain;

import java.util.UUID;

/**
 * UUID based entity, which the id will be created by its creation.
 *
 * @author lili
 */
public interface OperableEntity extends Entity<UUID> {

    /**
     * Set its id.
     *
     * @param id set entity id.
     */
    void setId(UUID id);
}
