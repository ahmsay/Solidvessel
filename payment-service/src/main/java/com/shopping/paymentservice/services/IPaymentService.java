package com.shopping.paymentservice.services;

import com.shopping.paymentservice.entity.Payment;

import java.util.List;
import java.util.Optional;

public interface IPaymentService {

    List<Payment> getAllPayments();

    Optional<Payment> getPaymentById(Long id);

    List<Payment> getPaymentsOfCustomer(Long customerId);
}
