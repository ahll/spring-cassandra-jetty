/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.api.security.controller;

import com.discovery.npd.api.security.controller.CreateCredentialApi.Request;
import com.discovery.npd.api.security.controller.ExistCredentialApiControllerTest.Config;
import com.discovery.npd.api.testing.TestServerInterface;
import com.discovery.npd.domain.security.Permission;
import com.discovery.npd.domain.security.credential.Credential;
import com.discovery.npd.domain.security.credential.CredentialService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import static org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE;

import java.util.UUID;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {Config.class})
public class ExistCredentialApiControllerTest {

    @Configuration
    @ComponentScan(
            basePackages = {"com.discovery.npd.api.security.controller"},
            useDefaultFilters = false,
            includeFilters = {
                @ComponentScan.Filter(type = ASSIGNABLE_TYPE, value = CreateCredentialApiController.class)
            })
    public static class Config implements UnitTestConfiguration {

        @Bean
        public CredentialService getCredential() {
            return new CredentialService() {
                @Override
                public Boolean exist(String email) {
                    return Boolean.TRUE;
                }

                @Override
                public Credential createCredential(String email, String passwordText) {
                    return null;
                }

                @Override
                public void changePassword(String email, String passwordText, String oldPasswordText) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public Credential verify(String email, String passwordText) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

				@Override
				public Credential verify(UUID token, Permission permission) {
					// TODO Auto-generated method stub
					return null;
				}

            };

        }
    }

    private TestServerInterface server;

    @Autowired
    CreateCredentialApiController controller;

    String url;

    @Before
    public void setUp() {
        url = CreateCredentialApi.URL;
        server = new TestServerInterface(controller);
    }

    @Test
    public void requestTest() throws Exception {
        Request request = new Request();
        String response = server.postResponseString(url, request);
    }

}
