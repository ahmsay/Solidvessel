package com.microshop.account.configuration;

import com.microshop.account.entity.Customer;
import com.microshop.account.repository.CustomerRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {

    @Bean
    InitializingBean seedDatabase(final CustomerRepository customerRepository) {
        return () -> {
            customerRepository.save(new Customer("Zorkov"));
            customerRepository.save(new Customer("Lorne"));
            customerRepository.save(new Customer("Matthias"));
        };
    }
}
