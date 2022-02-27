package com.microshop.auth.configuration.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AppUserAuthenticationProvider implements AuthenticationProvider {

    private final AppUserDetailsService userDetailsService;

    public AppUserAuthenticationProvider(AppUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        LoginToken loginToken = (LoginToken) authentication;
        String id = loginToken.getId();
        UserDetails userDetails = userDetailsService.loadUserByUsername(id);
        return new LoggedInToken(userDetails);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return LoginToken.class.isAssignableFrom(authentication);
    }
}
