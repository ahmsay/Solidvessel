package com.shopping.orderservice.repositories;

import com.shopping.orderservice.entity.Order;

import java.util.List;

public interface IOrderRepository {

    List<Order> getAllOrders();

    Order getOrderById(String id);
}
