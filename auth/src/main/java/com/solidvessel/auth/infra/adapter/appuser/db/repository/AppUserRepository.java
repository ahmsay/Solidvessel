package com.solidvessel.auth.infra.adapter.appuser.db.repository;

import com.solidvessel.auth.infra.adapter.appuser.db.entity.AppUserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUserJpaEntity, Long> {

    Optional<AppUserJpaEntity> findByUsername(String username);
}
