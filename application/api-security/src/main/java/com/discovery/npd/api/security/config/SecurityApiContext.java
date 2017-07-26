/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.api.security.config;

import com.discovery.npd.api.config.ApiJsonContext;
import com.discovery.npd.api.handling.ApiAuthenticator;
import com.discovery.npd.domain.security.credential.CredentialRepository;
import com.discovery.npd.domain.security.credential.CredentialService;
import com.discovery.npd.domain.security.token.TokenRepository;
import com.discovery.npd.persistence.security.CassandraCredentialRepository;
import com.discovery.npd.persistence.security.CassandraSecurityDataBaseHelper;
import com.discovery.npd.persistence.security.CassandraTokenRepository;
import com.discovery.npd.security.credential.UserCredentialService;
import java.io.IOException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { /**
     * controller component
     */
    "com.discovery.npd.api.security.controller"
})
public class SecurityApiContext extends ApiJsonContext {

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
    public CredentialService credentialService() {
        return new UserCredentialService(credentialRepository(), tokenRepository());
    }

    @Bean
    public ApiAuthenticator apiAuthenticator() throws IOException {
        return new ApiAuthenticator() {

            @Override
            protected Boolean verify(String token) {

                return Boolean.TRUE;
            }
        };
    }

}
