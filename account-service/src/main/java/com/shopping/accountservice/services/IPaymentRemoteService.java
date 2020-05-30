package com.shopping.accountservice.services;

import com.shopping.accountservice.entity.Payment;

import java.util.Set;

public interface IPaymentRemoteService {

    Set<Payment> getPaymentsOfCustomer(String customerId);
}
