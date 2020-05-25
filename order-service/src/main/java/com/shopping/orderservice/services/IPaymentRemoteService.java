package com.shopping.orderservice.services;

import com.shopping.orderservice.entity.Payment;

public interface IPaymentRemoteService {

    Payment getPaymentOfOrder(String orderId);
}
