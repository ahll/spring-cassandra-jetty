/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.api.handling;

import com.discovery.npd.domain.error.ErrorException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public abstract class ApiGet {

    abstract protected Response get() throws ErrorException;

    protected Boolean withoutSession = Boolean.FALSE;

    @Autowired
    ApiAuthenticator authenticator;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public Response get(HttpServletRequest httpServletRequest) throws IOException {
        Response response = Response.resolve(() -> authenticator.verify(this, httpServletRequest, withoutSession));
        ApiLogger.log(httpServletRequest, "", response);
        return response;
    }

}
