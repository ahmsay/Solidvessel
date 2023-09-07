package com.solidvessel.auth.infra.adapter.out.appuser.db;

import com.solidvessel.auth.domain.appuser.model.AppUser;
import com.solidvessel.auth.domain.appuser.port.AppUserQueryPort;
import com.solidvessel.auth.infra.adapter.out.appuser.db.entity.AppUserJpaEntity;
import com.solidvessel.auth.infra.adapter.out.appuser.db.repository.AppUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AppUserQueryDBAdapter implements AppUserQueryPort {

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
