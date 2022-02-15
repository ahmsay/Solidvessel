package com.microshop.payment.configuration;

import com.microshop.payment.entity.Payment;
import com.microshop.payment.repository.PaymentRepository;
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