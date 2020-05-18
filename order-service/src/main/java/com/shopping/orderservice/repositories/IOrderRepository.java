package com.shopping.orderservice.repositories;

import com.shopping.orderservice.entity.Order;

import java.util.Set;

public interface IOrderRepository {

    Set<Order> getAllOrders();

    Order getOrderById(int id);
}
