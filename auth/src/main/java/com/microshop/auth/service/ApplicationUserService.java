package com.microshop.auth.service;

import com.microshop.auth.entity.ApplicationUser;
import com.microshop.auth.repository.ApplicationUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
public class ApplicationUserService {

    private final ApplicationUserRepository applicationUserRepository;

    public ApplicationUserService(final ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }

    public ApplicationUser getById(final Long id) {
        return applicationUserRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found!"));
    }
}
