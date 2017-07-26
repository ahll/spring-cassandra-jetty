/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.persistence.security;

import com.discovery.npd.domain.security.credential.Credential;
import com.discovery.npd.domain.security.credential.CredentialRepository;
import com.discovery.npd.persistence.CassandraEntityRepository;
import static com.discovery.npd.persistence.security.CassandraCredentialTable.EMAIL_COLUMN;
import java.util.UUID;

/**
 *
 * @author Lili
 */
public class CassandraCredentialRepository
        extends CassandraEntityRepository<UUID, Credential, CassandraCredential>
        implements CredentialRepository {

    public CassandraCredentialRepository(
            CassandraSecurityDataBaseHelper dataSourceHelper) {

        mapper = dataSourceHelper.getDataMapper(CassandraCredential.class);
        session = mapper.getManager().getSession();
        keySpace = CassandraSecurityDataBaseHelper.DATA_BASE;
        tableName = CassandraCredentialTable.TABLE_NAME;
        idColumn = CassandraCredentialTable.ID_COLUMN;
    }

    /**
     *
     * {@inheritDoc}
     */
    @Override
    public Credential findByEmail(String email) {
        return find(EMAIL_COLUMN, email);
    }

    @Override
    protected CassandraCredential parse(Credential entity) {
        return CassandraCredential.clone(entity);
    }

}
