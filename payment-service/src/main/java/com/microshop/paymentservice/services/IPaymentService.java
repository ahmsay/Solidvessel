package com.microshop.paymentservice.services;

import com.microshop.paymentservice.dto.PaymentDTO;
import com.microshop.paymentservice.entity.Payment;

import java.util.List;

public interface IPaymentService {

    Iterable<Payment> findAll();

    PaymentDTO findById(Long id);

    Iterable<Payment> findByCustomerId(Long customerId);

    Payment save(Payment payment, List<Long> productIds);
}
