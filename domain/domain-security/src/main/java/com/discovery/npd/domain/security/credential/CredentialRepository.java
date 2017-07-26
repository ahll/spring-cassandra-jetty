/*
 * Copyright (c) 2017, discovery software and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.domain.security.credential;

import com.discovery.npd.domain.OperableEntityRepository;

/**
 * Credential repository to manager a credential.
 *
 * @author lili
 */
public interface CredentialRepository extends OperableEntityRepository<Credential> {

    /**
     *
     * @param email the registered email.
     * @return credential {@link Credential}
     */
    Credential findByEmail(String email);

}
