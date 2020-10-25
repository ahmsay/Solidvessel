package com.microshop.orderservice.services;

import com.microshop.orderservice.entity.Payment;

public interface IPaymentService {

    Payment findPaymentOfOrder(Long orderId);
}
