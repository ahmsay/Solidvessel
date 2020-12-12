package com.microshop.accountservice.services;

import com.microshop.accountservice.wrapper.Payment;

import java.util.List;

public interface IPaymentService {

    List<Payment> findByCustomerId(Long customerId);
}
