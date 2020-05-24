package com.shopping.paymentservice.services;

import com.shopping.paymentservice.entity.Payment;

import java.util.List;
import java.util.Set;

public interface IPaymentService {

    Set<Payment> getAllPayments();

    Payment getPaymentById(String id);

    List<Payment> getPaymentsByIds(List<String> paymentIds);
}
