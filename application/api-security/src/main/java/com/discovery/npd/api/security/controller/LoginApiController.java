/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.api.security.controller;

import com.discovery.npd.api.security.controller.CreateCredentialApi.Request;
import static com.discovery.npd.api.security.controller.LoginApi.URL;
import com.discovery.npd.api.handling.ApiPost;
import static com.discovery.npd.api.handling.ApiResponse.ok;
import com.discovery.npd.api.handling.Response;
import com.discovery.npd.domain.error.ErrorCode;
import com.discovery.npd.domain.error.Nulls;
import com.discovery.npd.domain.security.credential.Credential;
import com.discovery.npd.domain.security.credential.CredentialService;
import com.discovery.npd.domain.security.token.TokenRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Lilie
 */
@Controller
@RequestMapping(value = URL)
public class LoginApiController extends ApiPost<Request> {

    @Autowired
    CredentialService credentialService;

    @Autowired
    TokenRepository tokenRepository;

    public LoginApiController() {
        //withou session
        withoutSession = Boolean.TRUE;
    }

    @Override
    protected Response post(Request request) {

        Credential credential = credentialService.verify(request.getEmail(), request.getPassword());
        Nulls.requireNonNull(credential, ErrorCode.INVALID_CREDENTIAL);
        UUID token = tokenRepository.create(credential.getPermissionList(), TokenRepository.ONE_DAY, credential.getId());
        return ok(token);

    }
}
