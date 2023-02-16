package com.solidvessel.payment.infra.configuration;

import com.solidvessel.payment.infra.adapter.payment.db.entity.PaymentJpaEntity;
import com.solidvessel.payment.infra.adapter.payment.db.repository.PaymentRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Bean
    InitializingBean seedDatabase(final PaymentRepository paymentRepository) {
        return () -> {
            paymentRepository.save(new PaymentJpaEntity(10.5D, 1L));
            paymentRepository.save(new PaymentJpaEntity(200D, 2L));
            paymentRepository.save(new PaymentJpaEntity(999.99D, 2L));
        };
    }
}
