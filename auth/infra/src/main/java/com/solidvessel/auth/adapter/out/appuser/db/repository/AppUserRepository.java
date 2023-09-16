package com.solidvessel.auth.adapter.out.appuser.db.repository;

import com.solidvessel.auth.adapter.out.appuser.db.entity.AppUserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUserJpaEntity, Long> {

    Optional<AppUserJpaEntity> findByEmail(String email);

    boolean existsByEmail(String email);
}
