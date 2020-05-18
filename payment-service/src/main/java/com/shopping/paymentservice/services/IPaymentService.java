package com.shopping.paymentservice.services;

import com.shopping.paymentservice.entity.Payment;

import java.util.Set;

public interface IPaymentService {

    Set<Payment> getAllPayments();

    Payment getPaymentById(int id);
}
