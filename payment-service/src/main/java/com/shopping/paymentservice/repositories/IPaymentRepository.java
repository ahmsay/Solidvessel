package com.shopping.paymentservice.repositories;

import com.shopping.paymentservice.entity.Payment;

import java.util.List;

public interface IPaymentRepository {

    List<Payment> getAllPayments();

    Payment getPaymentById(String id);

    List<Payment> getPaymentsOfCustomer(String CustomerId);
}
