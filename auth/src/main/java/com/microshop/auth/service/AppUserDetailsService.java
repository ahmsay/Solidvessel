package com.microshop.auth.service;

import com.microshop.auth.entity.AppUser;
import com.microshop.shared.auth.AppUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final AppUserService applicationUserService;

    public AppUserDetailsService(AppUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = applicationUserService.getById(Long.parseLong(username));
        return new AppUserDetails(user.getId());
    }
}
