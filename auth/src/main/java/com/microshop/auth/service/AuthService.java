package com.microshop.auth.service;

import com.microshop.auth.authentication.LoginToken;
import com.microshop.auth.entity.AppUser;
import com.microshop.auth.repository.AppUserRepository;
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
    private final AppUserRepository appUserRepository;

    public AuthService(AuthenticationManager authenticationManager, AppUserRepository appUserRepository) {
        this.authenticationManager = authenticationManager;
        this.appUserRepository = appUserRepository;
    }

    public void login(final AppUser loginRequest) {
        AppUser appUser = appUserRepository.getByUsername(loginRequest.getUsername()).orElseThrow(() -> new RuntimeException("Invalid username or password!"));
        if (!appUser.getPassword().equals(loginRequest.getPassword())) {
            throw new RuntimeException("Invalid username or password!");
        }

        Authentication authentication = authenticationManager.authenticate(new LoginToken(appUser.getId().toString()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public void logout() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logoutHandler.logout(attr.getRequest(), attr.getResponse(), authentication);
    }
}
