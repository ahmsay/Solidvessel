package com.solidvessel.auth.infra.adapter.out.appuser.db;

import com.solidvessel.auth.domain.appuser.model.AppUser;
import com.solidvessel.auth.domain.appuser.port.AppUserPort;
import com.solidvessel.auth.infra.adapter.out.appuser.db.entity.AppUserJpaEntity;
import com.solidvessel.auth.infra.adapter.out.appuser.db.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppUserDBAdapter implements AppUserPort {

    private final AppUserRepository appUserRepository;

    @Override
    public Long save(final AppUser appUser) {
        AppUserJpaEntity appUserJpaEntity = appUserRepository.save(AppUserJpaEntity.from(appUser));
        return appUserJpaEntity.getId();
    }
}
