package com.microshop.orderservice.services;

import com.microshop.orderservice.wrapper.PaymentDTO;

public interface IPaymentService {

    PaymentDTO findById(Long id);
}
