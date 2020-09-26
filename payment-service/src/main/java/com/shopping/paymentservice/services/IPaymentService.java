package com.shopping.paymentservice.services;

import com.shopping.paymentservice.entity.Payment;

import java.util.List;

public interface IPaymentService {

    List<Payment> getAllPayments();

    Payment getPaymentById(long id);

    List<Payment> getPaymentsOfCustomer(long customerId);
}
