package com.shopping.paymentservice.services;

import com.shopping.paymentservice.entity.Order;

public interface IOrderRemoteService {

    Order getOrderOfPayment(String paymentId);
}
