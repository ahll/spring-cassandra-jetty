/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.api.platform.controller;

import com.discovery.npd.api.platform.controller.GetUserApiControllerTest.Config;
import com.discovery.npd.api.testing.TestServerInterface;
import com.discovery.npd.domain.platform.user.User;
import com.discovery.npd.domain.platform.user.UserRepository;
import com.discovery.npd.domain.security.Permission;
import com.discovery.npd.domain.security.credential.Credential;
import com.discovery.npd.domain.security.credential.CredentialService;
import java.util.List;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import static org.springframework.context.annotation.FilterType.ASSIGNABLE_TYPE;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {Config.class})
public class GetUserApiControllerTest {

    @Configuration
    @ComponentScan(
            basePackages = {"com.discovery.npd.api.platform.controller"},
            useDefaultFilters = false,
            includeFilters = {
                @ComponentScan.Filter(type = ASSIGNABLE_TYPE, value = GetUserApiController.class)
            })
    public static class Config implements UnitTestConfiguration {

        public class UserData implements User {

            private UUID id;
            private String nickname;

            @Override
            public UUID getId() {
                return id;
            }

            public void setId(UUID id) {
                this.id = id;
            }

            @Override
            public String getNickName() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

        }

        @Override
        public UserRepository userRepository() {
            return new UserRepository() {

                @Override
                public User one(UUID id) {
                    UserData data = new UserData();
                    data.setId(id);
                    data.setNickname("NickName");
                    return data;
                }

                @Override
                public void save(User entity) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public List<? extends User> all() {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public List<? extends User> in(List<UUID> keys) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

            };
        }

        @Override
        public CredentialService credentialService() {
            return new CredentialService() {
                @Override
                public Boolean exist(String email) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public Credential createCredential(String email, String passwordText) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void changePassword(String email, String passwordText, String oldPasswordText) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public Credential verify(String email, String passwordText) {
                    return null;
                }

                @Override
                public Credential verify(UUID token, Permission permission) {
                    return new Credential() {
                        @Override
                        public UUID getId() {
                            return UUID.randomUUID();
                        }

                        @Override
                        public String getEmail() {
                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }

                        @Override
                        public String getPassword() {
                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }

                        @Override
                        public List<Permission> getPermissionList() {
                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }

                        @Override
                        public void setId(UUID id) {
                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }

                    };
                }

            };
        }

    }

    private TestServerInterface server;

    @Autowired
    GetUserApiController controller;

    String url;

    @Before
    public void setUp() {
        url = GetUserApi.URL;
        server = new TestServerInterface(controller);
    }

    @Test
    public void requestTest() throws Exception {

        String response = server.getResponseString(url);

    }

}
