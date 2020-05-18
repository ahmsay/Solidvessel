package com.shopping.orderservice.services;

import com.shopping.orderservice.entity.Order;
import com.shopping.orderservice.repositories.IOrderRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OrderService implements IOrderService {

    private IOrderRepository orderRepository;

    public OrderService(final IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Set<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    @Override
    public Order getOrderById(final int id) {
        return orderRepository.getOrderById(id);
    }
}
