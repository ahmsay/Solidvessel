package com.microshop.accountservice.services;

import com.microshop.accountservice.wrapper.PaymentDTO;

import java.util.List;

public interface IPaymentService {

    List<PaymentDTO> findByCustomerId(Long customerId);
}
