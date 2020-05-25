package com.shopping.paymentservice.services;

import com.shopping.paymentservice.entity.Order;

public interface IOrderRemoteService {

    Order getOrderById(String id);
}
