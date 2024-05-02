package com.solidvessel.payment.adapter.out.payment.db;

import com.solidvessel.payment.adapter.out.payment.db.entity.PaymentJpaEntity;
import com.solidvessel.payment.adapter.out.payment.db.repository.PaymentRepository;
import com.solidvessel.payment.payment.model.Payment;
import com.solidvessel.payment.payment.port.PaymentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentDBAdapter implements PaymentPort {

    private final PaymentRepository paymentRepository;

    @Caching(evict = {
            @CacheEvict(value = "paymentsOfCustomer", key = "#payment.customerId"),
            @CacheEvict(value = "paymentsOfCustomer.rest", key = "#payment.customerId")
    })
    @Override
    public Long create(Payment payment) {
        PaymentJpaEntity paymentJpaEntity = paymentRepository.save(PaymentJpaEntity.from(payment));
        return paymentJpaEntity.getId();
    }

    @Caching(evict = {
            @CacheEvict(value = "paymentsOfCustomer", key = "#payment.customerId"),
            @CacheEvict(value = "paymentsOfCustomer.rest", key = "#payment.customerId"),
            @CacheEvict(value = "payment", key = "#payment.id"),
            @CacheEvict(value = "payment.rest", key = "#payment.id")
    })
    @Override
    public void update(Payment payment) {
        paymentRepository.save(PaymentJpaEntity.from(payment));
    }
}
