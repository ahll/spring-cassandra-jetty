/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.security.credential;

import com.discovery.npd.domain.security.Permission;
import com.discovery.npd.domain.security.credential.Credential;
import com.discovery.npd.domain.security.credential.CredentialRepository;
import com.discovery.npd.domain.security.credential.CredentialService;
import com.discovery.npd.domain.security.token.TokenRepository;
import java.util.UUID;

/**
 *
 * @author Lili
 */
public class UserCredentialService implements CredentialService {

    private final CredentialRepository credentialRepository;

    private final TokenRepository tokenRepository;

    public UserCredentialService(
            CredentialRepository credentialRepository,
            TokenRepository tokenRepository
    ) {
        this.credentialRepository = credentialRepository;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public Boolean exist(String email) {
        return credentialRepository.findByEmail(email) != null;
    }

    @Override
    public Credential createCredential(String email, String passwordText) {
        Credential credential = new UserCredential(email, passwordText);
        return credentialRepository.create(credential);
    }

    @Override
    public void changePassword(String email, String passwordText, String oldPasswordText) {
        Credential credential = credentialRepository.findByEmail(email);
        if (credential == null || !isSamePassword(credential, oldPasswordText)) {
            throw new IllegalArgumentException("OLD_PASSWORD_INVALID");
        }
        UserCredential credentialToUpdate = new UserCredential(credential);
        credentialToUpdate.setPasswordFromText(passwordText);
        credentialRepository.save(credential);

    }

    private boolean isSamePassword(Credential credential, String passwordText) {
        return Credential.generatePassword(passwordText).equals(credential.getPassword());
    }

    @Override
    public Credential verify(String email, String passwordText) {
        Credential credential = credentialRepository.findByEmail(email);
        if (credential != null && isSamePassword(credential, passwordText)) {
            return credential;
        }
        return null;
    }

    @Override
    public Credential verify(UUID token, Permission permission) {
        UUID credentialId = tokenRepository.exist(token, permission);
        Credential credential = credentialRepository.one(credentialId);
        return credential;
    }

}
