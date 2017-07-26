/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.persistence.security;

import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.discovery.npd.domain.security.Permission;
import static com.discovery.npd.persistence.security.CassandraSecurityDataBaseHelper.DATA_BASE;
import static com.discovery.npd.persistence.security.CassandraTokenAccessor.TABLE_NAME;
import java.util.UUID;

/**
 *
 * @author Lili
 */
@Table(keyspace = DATA_BASE, name = TABLE_NAME)
class CassandraToken {

    @PartitionKey(0)
    private UUID id;

    @PartitionKey(1)
    private Permission permission;
    
    private UUID credentialId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

	public UUID getCredentialId() {
		return credentialId;
	}

	public void setCredentialId(UUID credentialId) {
		this.credentialId = credentialId;
	}

}
