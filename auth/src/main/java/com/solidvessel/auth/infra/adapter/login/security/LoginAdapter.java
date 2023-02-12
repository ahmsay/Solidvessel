package com.solidvessel.auth.infra.adapter.login.security;

import com.solidvessel.auth.domain.login.port.LoginPort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class LoginAdapter implements LoginPort {

    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;

    public LoginAdapter(AuthenticationManager authenticationManager, SecurityContextRepository securityContextRepository) {
        this.authenticationManager = authenticationManager;
        this.securityContextRepository = securityContextRepository;
    }

    @Override
    public void login(String appUserId) {
        Authentication authentication = authenticationManager.authenticate(new LoginToken(appUserId));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        saveContext();
    }

    private void saveContext() {
        if (RequestContextHolder.getRequestAttributes() != null) {
            var request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            var response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            securityContextRepository.saveContext(SecurityContextHolder.getContext(), request, response);
        }
    }
}
