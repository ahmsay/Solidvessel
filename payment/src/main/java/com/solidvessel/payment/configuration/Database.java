package com.solidvessel.payment.configuration;

import com.solidvessel.payment.entity.Payment;
import com.solidvessel.payment.repository.PaymentRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {

    @Bean
    InitializingBean seedDatabase(final PaymentRepository paymentRepository) {
        return () -> {
            paymentRepository.save(new Payment(10.5D, 1L));
            paymentRepository.save(new Payment(200D, 2L));
            paymentRepository.save(new Payment(999.99D, 2L));
        };
    }
}
