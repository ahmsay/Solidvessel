package com.shopping.orderservice.services;

import com.shopping.orderservice.entity.Order;

import java.util.List;

public interface IOrderService {

    List<Order> getAllOrders();

    Order getOrderById(Long id);

    List<Order> getOrdersOfCustomer(Long customerId);
}
