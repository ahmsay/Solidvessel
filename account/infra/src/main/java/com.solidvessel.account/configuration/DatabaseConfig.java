package com.solidvessel.account.configuration;

import com.solidvessel.account.adapter.out.customer.db.entity.AddressEmbeddable;
import com.solidvessel.account.adapter.out.customer.db.entity.CustomerJpaEntity;
import com.solidvessel.account.adapter.out.customer.db.repository.CustomerRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DatabaseConfig {

    @Bean
    InitializingBean seedDatabase(final CustomerRepository customerRepository) {
        return () -> {
            customerRepository.save(new CustomerJpaEntity(1L, "Anakin", "Skywalker", LocalDate.now(), "vader_666@mail.com", "123", new ArrayList<>()));
            customerRepository.save(new CustomerJpaEntity(2L, "Lorne", "Malvo", LocalDate.now(), "malvo_lrn@mail.com", "456", new ArrayList<>()));
            customerRepository.save(new CustomerJpaEntity(3L, "Thomas", "Shelby", LocalDate.now(), "peaky_blinder@mail.com", "789", List.of(new AddressEmbeddable("home", "tr", "eskişehir", "26200"))));
        };
    }
}
