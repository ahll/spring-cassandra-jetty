/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.api.platform.config;

import com.discovery.npd.api.config.ApiJsonContext;
import com.discovery.npd.api.handling.ApiAuthenticator;
import com.discovery.npd.domain.error.Emptys;
import com.discovery.npd.domain.platform.user.UserRepository;
import com.discovery.npd.domain.security.Permission;
import com.discovery.npd.domain.security.credential.CredentialRepository;
import com.discovery.npd.domain.security.credential.CredentialService;
import com.discovery.npd.domain.security.token.TokenRepository;
import com.discovery.npd.persistence.platform.PlatformDataBaseHelper;
import com.discovery.npd.persistence.platform.user.CassandraUserRepository;
import com.discovery.npd.persistence.security.CassandraCredentialRepository;
import com.discovery.npd.persistence.security.CassandraSecurityDataBaseHelper;
import com.discovery.npd.persistence.security.CassandraTokenRepository;
import com.discovery.npd.security.credential.UserCredentialService;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { /**
     * controller component
     */
    "com.discovery.npd.api.platform.controller"
})
public class PlatformApiContext extends ApiJsonContext {

    @Bean
    public UserRepository userRepository() {

        PlatformDataBaseHelper helper = PlatformDataBaseHelper
                .getCassandraDataSourceHelper("localhost");

        return new CassandraUserRepository(helper);
    }

    @Bean
    public CredentialService credentialService() {
        return new UserCredentialService(credentialRepository(), tokenRepository());
    }

    @Bean
    public CredentialRepository credentialRepository() {

        CassandraSecurityDataBaseHelper helper = CassandraSecurityDataBaseHelper
                .getCassandraDataSourceHelper("localhost");

        return new CassandraCredentialRepository(helper);
    }

    @Bean
    public TokenRepository tokenRepository() {

        CassandraSecurityDataBaseHelper helper = CassandraSecurityDataBaseHelper
                .getCassandraDataSourceHelper("localhost");

        return new CassandraTokenRepository(helper);
    }

    @Bean
    public ApiAuthenticator apiAuthenticator() throws IOException {
        return new ApiAuthenticator() {

            @Autowired
            TokenRepository tokenRepository;

            @Override
            protected Boolean verify(String token) {

                if (Emptys.isNullOrEmpty(token)) {
                    return Boolean.FALSE;
                }

                UUID tokenId = UUID.fromString(token);
                return !Objects.isNull(tokenRepository.exist(tokenId, Permission.PLATFORM));
            }
        };
    }

}
