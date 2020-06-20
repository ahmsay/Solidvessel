package com.shopping.paymentservice.services;

import com.shopping.paymentservice.entity.Payment;

import java.util.List;

public interface IPaymentService {

    List<Payment> getAllPayments();

    Payment getPaymentById(String id);

    List<Payment> getPaymentsByIds(List<String> paymentIds);
}
