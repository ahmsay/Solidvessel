package com.solidvessel.order.configuration;

import com.solidvessel.order.adapter.out.order.db.entity.OrderJpaEntity;
import com.solidvessel.order.adapter.out.order.db.repository.OrderRepository;
import com.solidvessel.order.order.model.OrderStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Bean
    InitializingBean seedDatabase(final OrderRepository orderRepository) {
        return () -> {
            orderRepository.save(new OrderJpaEntity(1L, OrderStatus.DELIVERED, 1L, 1L));
            orderRepository.save(new OrderJpaEntity(2L, OrderStatus.ON_THE_WAY, 2L, 2L));
        };
    }
}
