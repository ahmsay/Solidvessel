package com.solidvessel.auth.adapter.out.appuser.db;

import com.solidvessel.auth.adapter.out.appuser.db.entity.AppUserJpaEntity;
import com.solidvessel.auth.adapter.out.appuser.db.repository.AppUserRepository;
import com.solidvessel.auth.appuser.model.AppUser;
import com.solidvessel.auth.appuser.port.AppUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AppUserDBAdapter implements AppUserPort {

    private final AppUserRepository appUserRepository;

    @Override
    public Long save(final AppUser appUser) {
        AppUserJpaEntity appUserJpaEntity = appUserRepository.save(AppUserJpaEntity.from(appUser));
        return appUserJpaEntity.getId();
    }
}
