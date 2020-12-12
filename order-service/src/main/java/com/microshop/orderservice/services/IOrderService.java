package com.microshop.orderservice.services;

import com.microshop.orderservice.entity.Order;
import com.microshop.orderservice.wrapper.OrderDTO;

public interface IOrderService {

    Iterable<Order> findAll();

    OrderDTO findById(Long id);

    Iterable<Order> findByCustomerId(Long customerId);

    Order save(Order order);
}
