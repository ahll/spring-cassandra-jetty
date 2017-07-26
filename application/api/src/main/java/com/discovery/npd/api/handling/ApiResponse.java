/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.api.handling;

import com.discovery.npd.domain.error.ErrorCode;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 *
 * @author Lili
 * @param <RESPONSEDATA>
 */
public class ApiResponse<RESPONSEDATA> implements Response {

    public static <RESPONSEDATA> ApiResponse<RESPONSEDATA> ok(RESPONSEDATA data) {
        ApiResponse<RESPONSEDATA> response = new ApiResponse<>();
        response.setStatus(Status.OK);
        response.setResponseData(data);
        return response;
    }

    public static ApiResponse ok() {
        ApiResponse response = new ApiResponse<>();
        response.setStatus(Status.OK);
        return response;
    }

    public static ApiResponse error(ErrorCode... error) {
        ApiResponse response = new ApiResponse<>();
        response.setStatus(Status.REQUEST_ERROR);
        response.setErrorList(
                Arrays.stream(error)
                        .map(Supplier::get)
                        .collect(Collectors.toList())
        );
        return response;
    }

    private Status status;
    private RESPONSEDATA responseData;
    private List<String> errorList;

    @Override
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public RESPONSEDATA getResponseData() {
        return responseData;
    }

    public void setResponseData(RESPONSEDATA responseData) {
        this.responseData = responseData;
    }

    @Override
    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }

    @Override
    public String toString() {
        return "ApiResponse{" + "status=" + status + ", responseData=" + responseData + ", errorList=" + errorList + '}';
    }

}
