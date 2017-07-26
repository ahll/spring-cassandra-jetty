/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.api.platform.controller;

import com.discovery.npd.api.platform.controller.SaveUserApi.Request;
import com.discovery.npd.api.platform.controller.SaveUserApiControllerTest.Config;
import com.discovery.npd.api.testing.TestServerInterface;
import com.discovery.npd.domain.platform.user.User;
import com.discovery.npd.domain.platform.user.UserRepository;
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
public class SaveUserApiControllerTest {

    @Configuration
    @ComponentScan(
            basePackages = {"com.discovery.npd.api.platform.controller"},
            useDefaultFilters = false,
            includeFilters = {
                @ComponentScan.Filter(type = ASSIGNABLE_TYPE, value = SaveUserApiController.class)
            })
    public static class Config implements UnitTestConfiguration {

        @Override
        public UserRepository userRepository() {
            return new UserRepository() {
                @Override
                public void save(User entity) {

                }

                @Override
                public List<? extends User> all() {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public User one(UUID key) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public List<? extends User> in(List<UUID> keys) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

            };
        }
    }

    private TestServerInterface server;

    @Autowired
    SaveUserApiController controller;

    String url;

    @Before
    public void setUp() {
        url = SaveUserApi.URL;
        server = new TestServerInterface(controller);
    }

    @Test
    public void requestTest() throws Exception {

        Request request = new Request();
        String response = server.postResponseString(url, request);
    }

}
