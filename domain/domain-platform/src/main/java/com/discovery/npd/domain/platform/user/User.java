/*
 * Copyright (c) 2017, discovery software and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.domain.platform.user;

import com.discovery.npd.domain.OperableEntity;
import java.util.UUID;

/**
 *
 * User class, model of user domain.
 *
 * @author Lili
 */
public interface User extends OperableEntity {

    /**
     *
     * @return its id
     */
    @Override
    UUID getId();

    /**
     *
     * @return nickname.
     */
    String getNickName();

}
