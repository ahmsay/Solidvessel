package com.microshop.auth.configuration.database;


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
            applicationUserRepository.save(new AppUser("Zorkov"));
            applicationUserRepository.save(new AppUser("Lorne"));
            applicationUserRepository.save(new AppUser("Matthias"));
        };
    }
}
