package com.solidvessel.account.configuration;

import com.solidvessel.account.entity.Customer;
import com.solidvessel.account.repository.CustomerRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {

    @Bean
    InitializingBean seedDatabase(final CustomerRepository customerRepository) {
        return () -> {
            customerRepository.save(new Customer(1L, "Anakin", "Skywalker"));
            customerRepository.save(new Customer(2L, "Lorne", "Malvo"));
            customerRepository.save(new Customer(3L, "Thomas", "Shelby"));
        };
    }
}
