package com.discovery.npd.domain.security.credential;

import com.discovery.npd.domain.security.Permission;
import java.util.UUID;

/**
 *
 * @author Lili
 */
public interface CredentialService {

    /**
     * Check if exist credential.
     *
     * @param email email of credential
     * @return if exist
     */
    Boolean exist(String email);

    /**
     * Create credential by email and password.
     *
     * @param email email
     * @param passwordText password in text
     * @return credential created
     */
    Credential createCredential(String email, String passwordText);

    /**
     * Change password of a credential.
     *
     * @param email its email.
     * @param passwordText its new password in text
     * @param oldPasswordText its old password in text
     */
    void changePassword(String email, String passwordText, String oldPasswordText);

    /**
     * Verify credential with email and password.
     *
     * @param email its email
     * @param passwordText its password in text
     * @return credential verified
     */
    Credential verify(String email, String passwordText);

    /**
     * Verify credential by token and permission.
     *
     * @param token access token
     * @param permission permission
     * @return credential {@link Credential}
     */
    Credential verify(UUID token, Permission permission);

}
