package com.microshop.inventoryservice.services;

import com.microshop.inventoryservice.entity.Payment;

public interface IPaymentService {

    Payment findById(Long id);
}
