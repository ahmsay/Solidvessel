package com.solidvessel.payment.adapter.out.payment.db;

import com.solidvessel.payment.adapter.out.payment.db.entity.PaymentJpaEntity;
import com.solidvessel.payment.adapter.out.payment.db.repository.PaymentRepository;
import com.solidvessel.payment.payment.model.Payment;
import com.solidvessel.payment.payment.port.PaymentQueryPort;
import com.solidvessel.shared.query.QueryOptions;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.solidvessel.shared.jpa.query.PageUtil.withPage;

@Repository
@RequiredArgsConstructor
public class PaymentDBQueryAdapter implements PaymentQueryPort {

    private final PaymentRepository paymentRepository;

    @Override
    public List<Payment> getPayments(QueryOptions queryOptions) {
        return paymentRepository.findAll(withPage(queryOptions)).stream().map(PaymentJpaEntity::toDomainModel).toList();
    }

    @Cacheable(value = "payment", key = "#id")
    @Override
    public Payment getById(Long id) {
        PaymentJpaEntity paymentJpaEntity = paymentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Payment not found!"));
        return paymentJpaEntity.toDomainModel();
    }

    @Cacheable(value = "paymentsOfCustomer", key = "#customerId")
    @Override
    public List<Payment> getByCustomerId(String customerId) {
        return paymentRepository.findByCustomerId(customerId).stream().map(PaymentJpaEntity::toDomainModel).toList();
    }
}
