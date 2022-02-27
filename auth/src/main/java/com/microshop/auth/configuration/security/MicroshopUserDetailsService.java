package com.microshop.auth.configuration.security;

import com.microshop.auth.entity.AppUser;
import com.microshop.auth.service.AppUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MicroshopUserDetailsService implements UserDetailsService {

    private final AppUserService applicationUserService;

    public MicroshopUserDetailsService(AppUserService applicationUserService) {
        this.applicationUserService = applicationUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = applicationUserService.getById(Long.parseLong(username));
        return new MicroshopUserDetails(user.getId());
    }
}
