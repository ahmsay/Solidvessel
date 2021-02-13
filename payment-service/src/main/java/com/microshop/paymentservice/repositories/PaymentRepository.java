package com.microshop.paymentservice.repositories;

import com.microshop.paymentservice.entity.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {

    Iterable<Payment> findByCustomerId(Long customerId);
}
