/*
 * Copyright (c) 2017, discovery software and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.domain.security.token;

import com.discovery.npd.domain.security.Permission;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Repository to save access token.
 *
 * @author Lili
 */
public interface TokenRepository {

    /**
     * One day in milli second.
     */
    public static Integer ONE_DAY = new Long(TimeUnit.DAYS.toSeconds(1)).intValue();

    /**
     * Create access token by permission list and TTL (time to live).
     *
     * @param permissionList permission list.
     * @param timeToLiveInSeconds TTL
     * @param credentialId credential id
     * @return Token created,
     */
    UUID create(List<Permission> permissionList, Integer timeToLiveInSeconds, UUID credentialId);

    /**
     * Check token and its permission.
     *
     * @param token access token
     * @param permission permission indicated
     * @return if exist.
     */
    UUID exist(UUID token, Permission permission);
}
