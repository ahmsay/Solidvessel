package com.shopping.accountservice.services;

import com.shopping.accountservice.entity.Payment;

import java.util.List;

public interface IPaymentService {

    List<Payment> getPaymentsOfCustomer(String customerId);
}
