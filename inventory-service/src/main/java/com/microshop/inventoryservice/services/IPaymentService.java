package com.microshop.inventoryservice.services;

import com.microshop.inventoryservice.wrapper.PaymentDTO;

public interface IPaymentService {

    PaymentDTO findById(Long id);
}
