package com.microshop.order.configuration;

import com.microshop.order.entity.Order;
import com.microshop.order.repository.OrderRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {

    @Bean
    InitializingBean seedDatabase(final OrderRepository orderRepository) {
        return () -> {
            orderRepository.save(new Order("Delivered", 1L, 1L));
            orderRepository.save(new Order("On the way", 2L, 2L));
        };
    }
}
