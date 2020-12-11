package com.microshop.orderservice.services;

import com.microshop.orderservice.dto.OrderDTO;
import com.microshop.orderservice.entity.Order;

public interface IOrderService {

    Iterable<Order> findAll();

    OrderDTO findById(Long id);

    Iterable<Order> findByCustomerId(Long customerId);

    Order save(Order order);
}
