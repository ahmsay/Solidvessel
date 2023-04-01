package com.solidvessel.account.infra.configuration;

import com.solidvessel.account.infra.adapter.customer.db.entity.CustomerJpaEntity;
import com.solidvessel.account.infra.adapter.customer.db.repository.CustomerRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DatabaseConfig {

    @Bean
    InitializingBean seedDatabase(final CustomerRepository customerRepository) {
        return () -> {
            customerRepository.save(new CustomerJpaEntity(1L, "Anakin", "Skywalker", LocalDate.now(), "vader_666@mail.com", "123"));
            customerRepository.save(new CustomerJpaEntity(2L, "Lorne", "Malvo", LocalDate.now(), "malvo_lrn@mail.com", "456"));
            customerRepository.save(new CustomerJpaEntity(3L, "Thomas", "Shelby", LocalDate.now(), "peaky_blinder@mail.com", "789"));
        };
    }
}
