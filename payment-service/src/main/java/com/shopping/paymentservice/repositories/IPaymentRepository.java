package com.shopping.paymentservice.repositories;

import com.shopping.paymentservice.entity.Payment;

import java.util.Set;

public interface IPaymentRepository {

    Set<Payment> getAllPayments();

    Payment getPaymentById(int id);
}
