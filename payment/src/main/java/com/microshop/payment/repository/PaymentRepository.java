package com.microshop.payment.repository;

import com.microshop.payment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByCustomerId(Long customerId);
}
