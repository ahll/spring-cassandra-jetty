/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.api.handling;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.discovery.npd.domain.error.ErrorException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public abstract class ApiPost<REQUEST> {

    @Autowired
    ApiAuthenticator authenticator;

    protected Boolean withoutSession = Boolean.FALSE;

    @Autowired
    private ObjectMapper mapper;

    protected Class<REQUEST> getClazz() {
        return (Class<REQUEST>) GenericTypeResolver
                .resolveTypeArgument(getClass(), ApiPost.class);
    }

    abstract protected Response post(REQUEST request) throws ErrorException;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public Response post(
            HttpServletRequest httpServletRequest,
            @RequestBody REQUEST request
    ) throws IOException {

        //resolve spring generic problem
        REQUEST req = mapper.convertValue(request, getClazz());

        Response response = Response.resolve(() -> authenticator.verify(this, httpServletRequest, req, withoutSession));
        ApiLogger.log(httpServletRequest, request, response);
        return response;
    }

}
