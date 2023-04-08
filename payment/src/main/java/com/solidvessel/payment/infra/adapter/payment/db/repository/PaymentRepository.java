package com.solidvessel.payment.infra.adapter.payment.db.repository;

import com.solidvessel.payment.infra.adapter.payment.db.entity.PaymentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<PaymentJpaEntity, Long> {

    List<PaymentJpaEntity> findByCustomerId(Long customerId);

    PaymentJpaEntity findByCustomerIdAndAccepted(Long customerId, boolean accepted);
}
