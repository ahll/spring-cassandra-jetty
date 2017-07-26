/*
 * Copyright (c) 2017, discovery software and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.domain.security.credential;

import com.discovery.npd.domain.OperableEntity;
import com.discovery.npd.domain.security.Permission;
import java.util.List;
import java.util.UUID;

/**
 *
 * Define a credential for user or other subject.
 *
 * @author Lili
 */
public interface Credential extends OperableEntity {

    /**
     * Hashing a given password.
     *
     * @param passwordInText password in text
     * @return passwaod in hash
     */
    static String generatePassword(String passwordInText) {
        return UUID.nameUUIDFromBytes(passwordInText.getBytes()).toString();
    }

    /**
     *
     * @return id of credential
     */
    UUID getId();

    /**
     *
     * @return email of credential
     */
    String getEmail();

    /**
     *
     * @return password of credential
     */
    String getPassword();

    /**
     *
     * @return permission list of credential
     */
    List<Permission> getPermissionList();

}
