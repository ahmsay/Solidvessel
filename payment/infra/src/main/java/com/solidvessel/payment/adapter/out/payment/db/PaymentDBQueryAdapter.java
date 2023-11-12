package com.solidvessel.payment.adapter.out.payment.db;

import com.solidvessel.payment.adapter.out.payment.db.entity.PaymentJpaEntity;
import com.solidvessel.payment.adapter.out.payment.db.repository.PaymentRepository;
import com.solidvessel.payment.payment.datamodel.PaymentDataModel;
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
    public List<PaymentDataModel> getAll() {
        return paymentRepository.findAll().stream().map(PaymentJpaEntity::toDataModel).toList();
    }

    @Override
    public PaymentDataModel getById(Long id) {
        PaymentJpaEntity paymentJpaEntity = paymentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Payment not found!"));
        return paymentJpaEntity.toDataModel();
    }

    @Override
    public List<PaymentDataModel> getByCustomerId(String customerId) {
        return paymentRepository.findByCustomerId(customerId).stream().map(PaymentJpaEntity::toDataModel).toList();
    }
}
