/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.api.handling;

import com.discovery.npd.domain.error.ErrorCode;
import com.discovery.npd.domain.error.Nulls;
import com.discovery.npd.domain.security.Permission;
import com.discovery.npd.domain.security.credential.CredentialService;
import java.util.Objects;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public abstract class ApiAuthenticator {

    abstract protected Boolean verify(String token);

    public static UUID getToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String id = extractToken(request);
        return Objects.isNull(id) ? null : UUID.fromString(id);
    }

    public static UUID getCredentialId(CredentialService service, Permission permission) {
        UUID token = getToken();
        return Nulls.resolve(() -> service.verify(token, permission).getId()).orElse(null);
    }

    private static String extractToken(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader("authorization");
    }

    protected Response verify(
            ApiGet api,
            HttpServletRequest httpServletRequest,
            Boolean notAuth
    ) {

        String token = extractToken(httpServletRequest);

        if (notAuth || verify(token)) {
            return api.get();
        } else {
            return ApiResponse.error(ErrorCode.INVALID_TOKEN);
        }

    }

    protected <REQUEST> Response verify(
            ApiPost<REQUEST> api,
            HttpServletRequest httpServletRequest,
            REQUEST request,
            Boolean notAuth
    ) {

        String token = extractToken(httpServletRequest);

        if (notAuth || verify(token)) {
            return api.post(request);
        } else {
            return ApiResponse.error(ErrorCode.INVALID_TOKEN);
        }

    }

}
