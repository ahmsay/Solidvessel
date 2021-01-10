package com.microshop.inventoryservice.services;

import com.microshop.inventoryservice.dto.PaymentDTO;

public interface IPaymentService {

    PaymentDTO findById(Long id);
}
