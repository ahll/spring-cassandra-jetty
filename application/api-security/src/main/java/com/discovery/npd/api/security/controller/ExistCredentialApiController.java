/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.api.security.controller;

import static com.discovery.npd.api.security.controller.ExistCredentialApi.*;
import com.discovery.npd.api.security.controller.ExistCredentialApi.Request;
import com.discovery.npd.api.handling.ApiPost;
import static com.discovery.npd.api.handling.ApiResponse.ok;
import com.discovery.npd.api.handling.Response;
import com.discovery.npd.domain.error.Booleans;
import com.discovery.npd.domain.error.ErrorCode;
import com.discovery.npd.domain.security.credential.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Lili
 */
@Controller
@RequestMapping(URL)
public class ExistCredentialApiController extends ApiPost<Request> {

    @Autowired
    CredentialService credentialService;

    @Override
    protected Response post(Request request) {
        Booleans.requireTrue(credentialService.exist(request.getEmail()), ErrorCode.NOT_FOUND_CREDENTIAL);
        return ok();
    }

}
