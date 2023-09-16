package com.solidvessel.shared.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;

public class LoggedInToken extends AbstractAuthenticationToken {

    private final UserDetails userDetails;

    public LoggedInToken(final UserDetails userDetails) {
        super(userDetails.getAuthorities());
        setAuthenticated(true);
        this.userDetails = userDetails;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return userDetails;
    }
}
