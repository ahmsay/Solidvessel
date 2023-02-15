package com.solidvessel.order.infra.configuration;

import com.solidvessel.order.infra.adapter.order.db.entity.OrderJpaEntity;
import com.solidvessel.order.infra.adapter.order.db.repository.OrderRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Bean
    InitializingBean seedDatabase(final OrderRepository orderRepository) {
        return () -> {
            orderRepository.save(new OrderJpaEntity("Delivered", 1L, 1L));
            orderRepository.save(new OrderJpaEntity("On the way", 2L, 2L));
        };
    }
}
