/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.api.platform.controller;

import com.discovery.npd.domain.platform.user.User;
import java.util.UUID;

/**
 *
 * @author daniel.eguia
 */
public class SaveUserApi {

    public final static String URL = "/api/platform/saveUser";

    public static class Request {

        public String nickName;

    }

    public static class SimpleUser implements User {

        public String nickName;
        public UUID id;

        @Override
        public String getNickName() {
            return nickName;
        }

        @Override
        public UUID getId() {
            return id;
        }

        @Override
        public void setId(UUID id) {
            this.id = id;
        }

    }

}
