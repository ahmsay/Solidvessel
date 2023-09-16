package com.solidvessel.auth.configuration.security;

import com.solidvessel.auth.appuser.model.AppUser;
import com.solidvessel.auth.appuser.port.AppUserQueryPort;
import com.solidvessel.shared.security.AppUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final AppUserQueryPort appUserQueryPort;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserQueryPort.getById(Long.parseLong(username));
        return new AppUserDetails(user.getId());
    }
}
