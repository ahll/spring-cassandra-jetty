/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.api.platform.controller;

import com.discovery.npd.api.handling.ApiAuthenticator;
import com.discovery.npd.api.handling.ApiGet;
import static com.discovery.npd.api.handling.ApiResponse.ok;
import com.discovery.npd.api.handling.Response;
import static com.discovery.npd.api.platform.controller.GetUserApi.URL;
import com.discovery.npd.domain.error.ErrorCode;
import com.discovery.npd.domain.error.Nulls;
import com.discovery.npd.domain.platform.user.User;
import com.discovery.npd.domain.platform.user.UserRepository;
import com.discovery.npd.domain.security.Permission;
import com.discovery.npd.domain.security.credential.Credential;
import com.discovery.npd.domain.security.credential.CredentialService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author daniel.eguia
 */
@Controller
@RequestMapping(URL)
public class GetUserApiController extends ApiGet {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CredentialService credentialService;

    public GetUserApiController() {

    }

    @Override
    protected Response get() {

        UUID token = ApiAuthenticator.getToken();
        Credential credential = credentialService.verify(token, Permission.PLATFORM);
        User user = userRepository.one(credential.getId());
        Nulls.requireNonNull(user, ErrorCode.NOT_FOUND_USER);
        return ok(user);

    }
}
