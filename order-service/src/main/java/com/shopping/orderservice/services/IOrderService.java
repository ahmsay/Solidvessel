package com.shopping.orderservice.services;

import com.shopping.orderservice.entity.Order;

import java.util.List;
import java.util.Set;

public interface IOrderService {

    Set<Order> getAllOrders();

    Order getOrderById(String id);

    List<Order> getOrdersByIds(List<String> orderIds);
}
