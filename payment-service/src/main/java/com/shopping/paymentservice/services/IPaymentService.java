package com.shopping.paymentservice.services;

import com.shopping.paymentservice.dto.PaymentDTO;
import com.shopping.paymentservice.entity.Payment;

public interface IPaymentService {

    Iterable<Payment> getAllPayments();

    Payment getPaymentById(Long id);

    Iterable<Payment> getPaymentsOfCustomer(Long customerId);

    Payment addPayment(PaymentDTO paymentDTO);
}
