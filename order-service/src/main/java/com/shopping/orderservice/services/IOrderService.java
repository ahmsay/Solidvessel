package com.shopping.orderservice.services;

import com.shopping.orderservice.entity.Order;

public interface IOrderService {

    Iterable<Order> findAll();

    Order findById(Long id);

    Iterable<Order> findByCustomerId(Long customerId);

    Order save(Order order);
}
