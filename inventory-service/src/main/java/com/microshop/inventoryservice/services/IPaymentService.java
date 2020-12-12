package com.microshop.inventoryservice.services;

import com.microshop.inventoryservice.wrapper.Payment;

public interface IPaymentService {

    Payment findById(Long id);
}
