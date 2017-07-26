/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.api.security.controller;

import static com.discovery.npd.api.security.controller.CreateCredentialApi.*;
import com.discovery.npd.api.security.controller.CreateCredentialApi.Request;
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
 * @author Lilie
 */
@Controller
@RequestMapping(value = URL)
public class CreateCredentialApiController extends ApiPost<Request> {

    @Autowired
    CredentialService credentialService;

    public CreateCredentialApiController() {
        //withou session
        withoutSession = Boolean.TRUE;
    }

    @Override
    protected Response post(Request request) {

        Booleans.requireFalse(credentialService.exist(request.getEmail()), ErrorCode.EXIST_CREDENTIAL);
        credentialService.createCredential(request.getEmail(), request.getPassword());
        return ok();

    }
}
