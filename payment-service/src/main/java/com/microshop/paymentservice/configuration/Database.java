package com.microshop.paymentservice.configuration;

import com.microshop.paymentservice.entity.Payment;
import com.microshop.paymentservice.entity.Sale;
import com.microshop.paymentservice.repository.PaymentRepository;
import com.microshop.paymentservice.repository.SaleRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {

    @Bean
    InitializingBean seedDatabase(final PaymentRepository paymentRepository, final SaleRepository saleRepository) {
        return () -> {
            paymentRepository.save(new Payment(10.5D, 1L));
            paymentRepository.save(new Payment(200D, 2L));
            paymentRepository.save(new Payment(999.99D, 2L));

            saleRepository.save(new Sale(1L, 1L));
            saleRepository.save(new Sale(1L, 2L));
            saleRepository.save(new Sale(1L, 3L));
            saleRepository.save(new Sale(2L, 4L));
            saleRepository.save(new Sale(3L, 1L));
            saleRepository.save(new Sale(3L, 5L));
            saleRepository.save(new Sale(3L, 6L));
        };
    }
}
