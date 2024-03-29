package com.solidvessel.payment.adapter.out.payment.db;

import com.solidvessel.payment.adapter.out.payment.db.entity.PaymentJpaEntity;
import com.solidvessel.payment.adapter.out.payment.db.repository.PaymentRepository;
import com.solidvessel.payment.payment.model.Payment;
import com.solidvessel.payment.payment.port.PaymentPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentDBAdapter implements PaymentPort {

    private final PaymentRepository paymentRepository;

    @Override
    public Long save(Payment payment) {
        PaymentJpaEntity paymentJpaEntity = paymentRepository.save(PaymentJpaEntity.from(payment));
        return paymentJpaEntity.getId();
    }
}
