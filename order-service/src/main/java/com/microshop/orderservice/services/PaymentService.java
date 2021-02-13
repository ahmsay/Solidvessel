package com.microshop.orderservice.services;

import com.microshop.orderservice.dto.PaymentDTO;

public interface PaymentService {

    PaymentDTO findById(Long id);
}
