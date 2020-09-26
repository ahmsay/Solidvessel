package com.shopping.paymentservice.repositories;

import com.shopping.paymentservice.entity.Payment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IPaymentRepository extends CrudRepository<Payment, Long> {

    List<Payment> findAll();

    Payment findById(long id);

    List<Payment> findByCustomerId(long customerId);
}
