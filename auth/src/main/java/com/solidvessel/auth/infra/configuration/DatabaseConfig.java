package com.solidvessel.auth.infra.configuration;


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
            applicationUserRepository.save(new AppUserJpaEntity("vader_666", "i_hate_sand"));
            applicationUserRepository.save(new AppUserJpaEntity("malvo_lrn", "sioux_falls"));
            applicationUserRepository.save(new AppUserJpaEntity("peaky_blinder", "garrison_1918"));
        };
    }
}
