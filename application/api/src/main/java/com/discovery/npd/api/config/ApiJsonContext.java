/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.discovery.npd.api.handling.ApiLogger;
import java.io.IOException;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Web application resource configuration for JSON style application.
 *
 * @author Lili
 */
@Configuration
@EnableScheduling
@EnableWebMvc
public class ApiJsonContext extends WebMvcConfigurationSupport {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

    @Bean
    public ObjectMapper getObjectMapper() throws IOException {
        return new ObjectMapper();
    }

    @Bean
    public Logger getLogger() throws IOException {
        return ApiLogger.LOGGER;
    }

    @Override
    protected void configureMessageConverters(
            List<HttpMessageConverter<?>> converters
    ) {
        converters.add(converter());
        addDefaultHttpMessageConverters(converters);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         * Client resource mapping
         */

//        registry.addResourceHandler("/commons/image/**")
//                .addResourceLocations("/commons/picture/");
    }

    @Bean
    protected MappingJackson2HttpMessageConverter converter() {

        /**
         * converter for Joson
         */
        MappingJackson2HttpMessageConverter converter
                = new MappingJackson2HttpMessageConverter();
        return converter;
    }

}
