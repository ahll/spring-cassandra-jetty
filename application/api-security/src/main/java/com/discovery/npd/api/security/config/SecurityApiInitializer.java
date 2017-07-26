/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.api.security.config;

import com.discovery.npd.api.config.ApiInitializer;

/**
 * Spring MVC project, and start up configuration. It configure all requirements
 * for java servlet 3.0 .
 *
 * @author Lili
 */
public class SecurityApiInitializer extends ApiInitializer {

    @Override
    protected Class<?> getApplicationContext() {
        return SecurityApiContext.class; //To change body of generated methods, choose Tools | Templates.
    }

}
