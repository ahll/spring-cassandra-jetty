/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.api.platform.controller;

import com.discovery.npd.api.platform.controller.SaveUserApi.Request;
import com.discovery.npd.api.platform.controller.SaveUserApi.SimpleUser;
import static com.discovery.npd.api.platform.controller.SaveUserApi.URL;
import com.discovery.npd.api.handling.ApiAuthenticator;
import com.discovery.npd.api.handling.ApiPost;
import static com.discovery.npd.api.handling.ApiResponse.ok;
import com.discovery.npd.api.handling.Response;
import com.discovery.npd.domain.platform.user.UserRepository;
import com.discovery.npd.domain.security.Permission;
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
@RequestMapping(value = URL)
public class SaveUserApiController extends ApiPost<Request> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CredentialService credentialService;

    public SaveUserApiController() {

    }

    @Override
    protected Response post(Request request) {

        UUID credentialId = ApiAuthenticator.getCredentialId(credentialService, Permission.PLATFORM);

        SimpleUser user = new SimpleUser();
        user.setId(credentialId);
        user.nickName = request.nickName;
        userRepository.save(user);
        return ok();

    }
}
