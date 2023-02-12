package com.solidvessel.auth.infra.adapter.login.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class LoginToken extends AbstractAuthenticationToken {

    private final String id;

    public LoginToken(final String id) {
        super(null);
        setAuthenticated(false);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return id;
    }
}
