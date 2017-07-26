/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.api.testing;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.discovery.npd.api.handling.ApiResponse;
import java.io.IOException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * Test service interface
 *
 * @author Lili
 */
public class TestServerInterface {

    private final MockMvc mockMvc;
    private final ObjectMapper mapper;
    private final HttpHeaders headers = new HttpHeaders();

    public void addHeader(String headerName, String headerValue) {
        headers.add(headerName, headerValue);
    }

    public void clearHeaders() {
        headers.clear();
    }

    public <CONTROLLER> TestServerInterface(CONTROLLER controller) {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    public String getResponseString(String url) throws IOException, Exception {

        String response = mockMvc.perform(
                MockMvcRequestBuilders.get(url)
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();
        return response;
    }

    public <REQUEST> String postResponseString(String url, REQUEST request) throws IOException, Exception {

        byte[] json = mapper.writeValueAsBytes(request);

        String response = mockMvc.perform(
                MockMvcRequestBuilders.post(url)
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse()
                .getContentAsString();
        return response;
    }

    public <RESPONSEDATA, REQUEST> ApiResponse<RESPONSEDATA> postResponse(String url, REQUEST request, Class<RESPONSEDATA> responseDataClass) throws IOException, Exception {

        String response = postResponseString(url, request);
        return toResponse(response, responseDataClass);
    }

    public <RESPONSEDATA> ApiResponse<RESPONSEDATA> getResponse(String url, Class<RESPONSEDATA> responseDataClass) throws IOException, Exception {

        String response = getResponseString(url);
        return toResponse(response, responseDataClass);
    }

    public <REQUEST> ApiResponse postOkResponse(String url, REQUEST request) throws IOException, Exception {
        String response = postResponseString(url, request);
        return mapper.readValue(response, ApiResponse.class);
    }

    private <RESPONSEDATA> ApiResponse<RESPONSEDATA> toResponse(String json, Class<RESPONSEDATA> clazz) throws IOException {

        JavaType type = mapper.getTypeFactory().constructParametricType(ApiResponse.class, clazz);
        return mapper.readValue(json, type);
    }

}
