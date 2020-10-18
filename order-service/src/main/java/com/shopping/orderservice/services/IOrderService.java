package com.shopping.orderservice.services;

import com.shopping.orderservice.entity.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderService {

    List<Order> getAllOrders();

    Optional<Order> getOrderById(Long id);

    List<Order> getOrdersOfCustomer(Long customerId);
}
