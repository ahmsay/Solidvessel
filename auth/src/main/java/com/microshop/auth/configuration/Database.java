package com.microshop.auth.configuration;


import com.microshop.auth.entity.AppUser;
import com.microshop.auth.repository.AppUserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {

    @Bean
    InitializingBean seedDatabase(final AppUserRepository applicationUserRepository) {
        return () -> {
            applicationUserRepository.save(new AppUser(1L, "vader_666", "i_hate_sand"));
            applicationUserRepository.save(new AppUser(2L, "malvo_lrn", "sioux_falls"));
            applicationUserRepository.save(new AppUser(3L, "peaky_blinder", "garrison_1918"));
        };
    }
}
