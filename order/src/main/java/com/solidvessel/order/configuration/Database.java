package com.solidvessel.order.configuration;

import com.solidvessel.order.entity.CustomerOrder;
import com.solidvessel.order.repository.OrderRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {

    @Bean
    InitializingBean seedDatabase(final OrderRepository orderRepository) {
        return () -> {
            orderRepository.save(new CustomerOrder("Delivered", 1L, 1L));
            orderRepository.save(new CustomerOrder("On the way", 2L, 2L));
        };
    }
}
