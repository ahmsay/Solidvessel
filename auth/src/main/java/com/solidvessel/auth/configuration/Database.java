package com.solidvessel.auth.configuration;


import com.solidvessel.auth.entity.AppUser;
import com.solidvessel.auth.repository.AppUserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {

    @Bean
    InitializingBean seedDatabase(final AppUserRepository applicationUserRepository) {
        return () -> {
            applicationUserRepository.save(new AppUser("vader_666", "i_hate_sand"));
            applicationUserRepository.save(new AppUser("malvo_lrn", "sioux_falls"));
            applicationUserRepository.save(new AppUser("peaky_blinder", "garrison_1918"));
        };
    }
}
