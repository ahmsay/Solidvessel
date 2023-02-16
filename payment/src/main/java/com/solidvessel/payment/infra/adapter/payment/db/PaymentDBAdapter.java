package com.solidvessel.payment.infra.adapter.payment.db;

import com.solidvessel.payment.domain.payment.datamodel.PaymentDataModel;
import com.solidvessel.payment.domain.payment.model.Payment;
import com.solidvessel.payment.domain.payment.port.PaymentPort;
import com.solidvessel.payment.infra.adapter.payment.db.entity.PaymentJpaEntity;
import com.solidvessel.payment.infra.adapter.payment.db.repository.PaymentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentDBAdapter implements PaymentPort {

    private final PaymentRepository paymentRepository;

    public PaymentDBAdapter(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

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
    public List<PaymentDataModel> getByCustomerId(Long customerId) {
        return paymentRepository.findByCustomerId(customerId).stream().map(PaymentJpaEntity::toDataModel).toList();
    }

    @Override
    public Long add(Payment payment) {
        PaymentJpaEntity paymentJpaEntity = paymentRepository.save(PaymentJpaEntity.from(payment));
        return paymentJpaEntity.getId();
    }
}
