package com.shopping.orderservice.services;

import com.shopping.orderservice.entity.Order;

public interface IOrderService {

    Iterable<Order> getAllOrders();

    Order getOrderById(Long id);

    Iterable<Order> getOrdersOfCustomer(Long customerId);

    Order addOrder(Order order);
}
