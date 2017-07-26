/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.persistence.security;

import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.discovery.npd.domain.security.Permission;
import com.discovery.npd.domain.security.credential.Credential;
import static com.discovery.npd.persistence.security.CassandraCredentialTable.TABLE_NAME;
import static com.discovery.npd.persistence.security.CassandraSecurityDataBaseHelper.DATA_BASE;
import java.util.List;
import java.util.UUID;

/**
 *
 * @inheritDoc
 *
 * @author Lili
 */
@Table(keyspace = DATA_BASE, name = TABLE_NAME)
public class CassandraCredential implements Credential {

    public static CassandraCredential clone(Credential credential) {
        CassandraCredential cassandraCredential
                = new CassandraCredential();
        cassandraCredential.setId(credential.getId());
        cassandraCredential.setEmail(credential.getEmail());
        cassandraCredential.setPassword(credential.getPassword());
        cassandraCredential.setPermissionList(credential.getPermissionList());
        return cassandraCredential;
    }

    @PartitionKey
    private UUID id;
    private String email;
    private String password;
    private List<Permission> permissionList;

    @Override
    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
