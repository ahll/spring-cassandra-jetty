/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.api.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Spring MVC project, and start up configuration. It configure all requirements
 * for java servlet 3.0 .
 *
 * @author Lili
 */
public abstract class ApiInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{getApplicationContext()};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/*"};
    }

    protected abstract Class<?> getApplicationContext();

}
