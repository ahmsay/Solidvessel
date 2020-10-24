package com.shopping.paymentservice.services;

import com.shopping.paymentservice.dto.PaymentDTO;
import com.shopping.paymentservice.entity.Payment;

public interface IPaymentService {

    Iterable<Payment> findAll();

    Payment findById(Long id);

    Iterable<Payment> findByCustomerId(Long customerId);

    Payment save(PaymentDTO paymentDTO);
}
