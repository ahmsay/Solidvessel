package com.microshop.auth.service;

import com.microshop.auth.entity.AppUser;
import com.microshop.auth.repository.AppUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
public class AppUserService {

    private final AppUserRepository applicationUserRepository;

    public AppUserService(final AppUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    public AppUser getById(final Long id) {
        return applicationUserRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found!"));
    }
}
