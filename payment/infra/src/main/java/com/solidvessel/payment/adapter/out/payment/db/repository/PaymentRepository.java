package com.solidvessel.payment.adapter.out.payment.db.repository;

import com.solidvessel.payment.adapter.out.payment.db.entity.PaymentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<PaymentJpaEntity, Long>, PagingAndSortingRepository<PaymentJpaEntity, Long> {

    List<PaymentJpaEntity> findByCustomerId(String customerId);
}
