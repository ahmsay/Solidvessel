package com.solidvessel.payment.adapter.out.payment.db;

import com.solidvessel.payment.adapter.out.payment.db.entity.PaymentJpaEntity;
import com.solidvessel.payment.adapter.out.payment.db.repository.PaymentRepository;
import com.solidvessel.payment.payment.model.Payment;
import com.solidvessel.payment.payment.port.PaymentQueryPort;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PaymentDBQueryAdapter implements PaymentQueryPort {

    private final PaymentRepository paymentRepository;

    @Override
    public List<Payment> getAll() {
        return paymentRepository.findAll().stream().map(PaymentJpaEntity::toDomainModel).toList();
    }

    @Override
    public Payment getById(Long id) {
        PaymentJpaEntity paymentJpaEntity = paymentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Payment not found!"));
        return paymentJpaEntity.toDomainModel();
    }

    @Override
    public List<Payment> getByCustomerId(String customerId) {
        return paymentRepository.findByCustomerId(customerId).stream().map(PaymentJpaEntity::toDomainModel).toList();
    }
}
