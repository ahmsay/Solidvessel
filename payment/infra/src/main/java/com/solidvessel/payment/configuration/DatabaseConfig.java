package com.solidvessel.payment.configuration;

import com.solidvessel.payment.adapter.out.cart.db.entity.CartJpaEntity;
import com.solidvessel.payment.adapter.out.cart.db.repository.CartRepository;
import com.solidvessel.payment.adapter.out.payment.db.entity.PaymentJpaEntity;
import com.solidvessel.payment.adapter.out.payment.db.entity.ProductEmbeddable;
import com.solidvessel.payment.adapter.out.payment.db.repository.PaymentRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class DatabaseConfig {

    @Bean
    InitializingBean seedDatabase(final PaymentRepository paymentRepository, final CartRepository cartRepository) {
        return () -> {
            paymentRepository.save(new PaymentJpaEntity(null, 1L, List.of(new ProductEmbeddable(1L, 1, "Lol", 25D)), 250D));
            paymentRepository.save(new PaymentJpaEntity(null, 2L, List.of(new ProductEmbeddable(2L, 1, "asd", 3D)), 3D));
            paymentRepository.save(new PaymentJpaEntity(null, 3L, List.of(new ProductEmbeddable(2l, 3, "zxc", 23.4D)), 70.2D));

            cartRepository.save(new CartJpaEntity(null, 1L, new ArrayList<>()));
            cartRepository.save(new CartJpaEntity(null, 2L, new ArrayList<>()));
            cartRepository.save(new CartJpaEntity(null, 3L, new ArrayList<>()));
        };
    }
}
