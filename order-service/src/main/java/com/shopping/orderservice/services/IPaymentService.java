package com.shopping.orderservice.services;

import com.shopping.orderservice.entity.Payment;

public interface IPaymentService {

    Payment getPaymentOfOrder(String orderId);
}
