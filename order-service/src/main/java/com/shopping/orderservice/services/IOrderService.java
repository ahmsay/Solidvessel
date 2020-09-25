package com.shopping.orderservice.services;

import com.shopping.orderservice.entity.Order;

import java.util.List;

public interface IOrderService {

    List<Order> getAllOrders();

    Order getOrderById(String id);

    List<Order> getOrdersOfCustomer(String customerId);
}
