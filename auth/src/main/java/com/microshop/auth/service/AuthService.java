package com.microshop.auth.service;

import com.microshop.auth.authentication.LoginToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;

    public AuthService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public void login(final Long id) {
        Authentication authentication = authenticationManager.authenticate(new LoginToken(id.toString()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public void logout() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logoutHandler.logout(attr.getRequest(), attr.getResponse(), authentication);
    }
}
