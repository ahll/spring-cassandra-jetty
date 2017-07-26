/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.security.credential;

import com.google.common.collect.Lists;
import com.discovery.npd.domain.security.Permission;
import com.discovery.npd.domain.security.credential.Credential;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Lili
 */
public class UserCredential implements Credential {

    private UUID id;

    private String email;

    private String password;

    private List<Permission> permissionList;

    UserCredential(String email, String passwordText) {
        this.id = null;
        this.email = email;
        this.password = Credential.generatePassword(passwordText);
        permissionList = Lists.newArrayList(Permission.values());
    }

    UserCredential(Credential credential) {
        this.id = credential.getId();
        this.email = credential.getEmail();
        this.password = credential.getPassword();
        permissionList = credential.getPermissionList();
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordFromText(String passwordText) {
        this.password = Credential.generatePassword(passwordText);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public List<Permission> getPermissionList() {
        return permissionList;
    }

}
