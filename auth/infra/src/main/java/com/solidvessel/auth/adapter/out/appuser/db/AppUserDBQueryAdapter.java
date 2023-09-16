package com.solidvessel.auth.adapter.out.appuser.db;

import com.solidvessel.auth.adapter.out.appuser.db.entity.AppUserJpaEntity;
import com.solidvessel.auth.adapter.out.appuser.db.repository.AppUserRepository;
import com.solidvessel.auth.appuser.model.AppUser;
import com.solidvessel.auth.appuser.port.AppUserQueryPort;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AppUserDBQueryAdapter implements AppUserQueryPort {

    private final AppUserRepository appUserRepository;

    @Override
    public AppUser getById(final Long id) {
        AppUserJpaEntity appUserJpaEntity = appUserRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found!"));
        return appUserJpaEntity.toDomainModel();
    }

    @Override
    public AppUser getByEmail(final String email) {
        AppUserJpaEntity appUserJpaEntity = appUserRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("User not found!"));
        return appUserJpaEntity.toDomainModel();
    }

    @Override
    public boolean isEmailRegistered(String email) {
        return appUserRepository.existsByEmail(email);
    }
}
