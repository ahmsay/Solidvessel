package com.solidvessel.payment.infra.adapter.out.payment.db;

import com.solidvessel.payment.domain.payment.model.Payment;
import com.solidvessel.payment.domain.payment.port.PaymentPort;
import com.solidvessel.payment.infra.adapter.out.payment.db.entity.PaymentJpaEntity;
import com.solidvessel.payment.infra.adapter.out.payment.db.repository.PaymentRepository;
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
