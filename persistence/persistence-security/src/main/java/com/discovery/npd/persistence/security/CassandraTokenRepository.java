/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.persistence.security;

import com.datastax.driver.mapping.Mapper;
import com.discovery.npd.domain.security.Permission;
import com.discovery.npd.domain.security.token.TokenRepository;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 *
 * @author Lili
 */
public class CassandraTokenRepository implements TokenRepository {

    private final Mapper<CassandraToken> mapper;
    private final CassandraTokenAccessor accessor;

    public CassandraTokenRepository(CassandraSecurityDataBaseHelper dataSourceHelper) {

        mapper = dataSourceHelper.getDataMapper(CassandraToken.class);
        accessor = dataSourceHelper.createAccessor(CassandraTokenAccessor.class);
    }

    @Override
    public UUID create(List<Permission> permissionList, Integer timeToLiveInSeconds, UUID credentialId) {
        UUID id = UUID.randomUUID();
        List<CassandraToken> result = accessor.getTokenResult(id).all();
        while (result != null && !result.isEmpty()) {
            id = UUID.randomUUID();
        }
        for (Permission permission : permissionList) {
            CassandraToken token = new CassandraToken();
            token.setPermission(permission);
            token.setId(id);
            token.setCredentialId(credentialId);
            mapper.save(token, Mapper.Option.ttl(timeToLiveInSeconds));
        }
        return id;
    }

    @Override
    public UUID exist(UUID token, Permission permission) {
        CassandraToken tokenObject = mapper.get(token, permission);
        return Objects.isNull(tokenObject) ? null : tokenObject.getCredentialId();

    }

}
