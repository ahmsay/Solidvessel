package com.solidvessel.auth.infra.configuration;


import com.solidvessel.auth.infra.adapter.appuser.db.entity.ActivityEmbeddable;
import com.solidvessel.auth.infra.adapter.appuser.db.entity.AppUserJpaEntity;
import com.solidvessel.auth.infra.adapter.appuser.db.repository.AppUserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Bean
    InitializingBean seedDatabase(final AppUserRepository applicationUserRepository) {
        return () -> {
            applicationUserRepository.save(new AppUserJpaEntity(1L, "vader_666@mail.com", "i_hate_sand", new ActivityEmbeddable(true, null)));
            applicationUserRepository.save(new AppUserJpaEntity(2L, "malvo_lrn@mail.com", "sioux_falls", new ActivityEmbeddable(true, null)));
            applicationUserRepository.save(new AppUserJpaEntity(3L, "peaky_blinder@mail.com", "garrison_1918", new ActivityEmbeddable(true, null)));
        };
    }
}
