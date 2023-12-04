package com.solidvessel.shared.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class SessionUtil {

    public static String getCurrentUserToken() {
        var requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return requestAttributes.getRequest().getHeader("authorization");
    }

    public static String getCurrentUserId() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
