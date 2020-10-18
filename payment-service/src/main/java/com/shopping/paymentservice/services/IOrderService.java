package com.shopping.paymentservice.services;

import com.shopping.paymentservice.entity.Order;

public interface IOrderService {

    Order getOrderOfPayment(Long paymentId);
}
