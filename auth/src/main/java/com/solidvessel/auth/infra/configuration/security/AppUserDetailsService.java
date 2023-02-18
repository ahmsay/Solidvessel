package com.solidvessel.auth.infra.configuration.security;

import com.solidvessel.auth.domain.appuser.model.AppUser;
import com.solidvessel.auth.domain.appuser.port.AppUserPort;
import com.solidvessel.shared.infra.security.AppUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final AppUserPort appUserPort;

    public AppUserDetailsService(AppUserPort appUserPort) {
        this.appUserPort = appUserPort;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserPort.getById(Long.parseLong(username));
        return new AppUserDetails(user.getId());
    }
}
