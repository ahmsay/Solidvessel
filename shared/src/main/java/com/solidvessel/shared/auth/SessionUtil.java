package com.solidvessel.shared.auth;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class SessionUtil {

    public static String getSession(final HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Cookie sessionCookie = Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals("SESSION"))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Session not found!"));
        return "SESSION=" + sessionCookie.getValue();
    }
}
