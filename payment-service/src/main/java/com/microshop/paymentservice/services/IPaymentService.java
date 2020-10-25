package com.microshop.paymentservice.services;

import com.microshop.paymentservice.dto.PaymentDTO;
import com.microshop.paymentservice.entity.Payment;

public interface IPaymentService {

    Iterable<Payment> findAll();

    Payment findById(Long id);

    Iterable<Payment> findByCustomerId(Long customerId);

    Payment save(PaymentDTO paymentDTO);
}
