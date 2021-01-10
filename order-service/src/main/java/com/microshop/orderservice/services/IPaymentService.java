package com.microshop.orderservice.services;

import com.microshop.orderservice.dto.PaymentDTO;

public interface IPaymentService {

    PaymentDTO findById(Long id);
}
