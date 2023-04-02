package com.solidvessel.shared.infra.util;

import jakarta.servlet.http.Cookie;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

public class SessionUtil {

    public static String getCurrentUserSession() {
        var requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        Cookie[] cookies = requestAttributes.getRequest().getCookies();
        Cookie sessionCookie = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals("SESSION"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Session not found!"));
        return "SESSION=" + sessionCookie.getValue();
    }

    public static Long getCurrentUserId() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return Long.parseLong(userName);
    }
}
