package com.solidvessel.account.infra.configuration;

import com.solidvessel.account.infra.adapter.customer.db.entity.CustomerJpaEntity;
import com.solidvessel.account.infra.adapter.customer.db.repository.CustomerRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Bean
    InitializingBean seedDatabase(final CustomerRepository customerRepository) {
        return () -> {
            customerRepository.save(new CustomerJpaEntity(1L, "Anakin", "Skywalker"));
            customerRepository.save(new CustomerJpaEntity(2L, "Lorne", "Malvo"));
            customerRepository.save(new CustomerJpaEntity(3L, "Thomas", "Shelby"));
        };
    }
}
