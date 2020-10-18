package com.shopping.paymentservice.repositories;

import com.shopping.paymentservice.entity.Payment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IPaymentRepository extends CrudRepository<Payment, Long> {

    List<Payment> findAll();

    Optional<Payment> findById(Long id);

    List<Payment> findByCustomerId(Long customerId);
}
