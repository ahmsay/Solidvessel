package com.microshop.accountservice.services;

import com.microshop.accountservice.entity.Payment;

import java.util.List;

public interface IPaymentService {

    List<Payment> findByCustomerId(Long customerId);
}
