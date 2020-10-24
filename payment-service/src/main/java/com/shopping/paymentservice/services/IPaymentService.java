package com.shopping.paymentservice.services;

import com.shopping.paymentservice.dto.PaymentDTO;
import com.shopping.paymentservice.entity.Payment;

import java.util.List;

public interface IPaymentService {

    List<Payment> getAllPayments();

    Payment getPaymentById(Long id);

    List<Payment> getPaymentsOfCustomer(Long customerId);

    Payment addPayment(PaymentDTO paymentDTO);
}
