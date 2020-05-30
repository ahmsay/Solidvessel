package com.shopping.orderservice.services;

import com.shopping.orderservice.entity.Order;
import com.shopping.orderservice.repositories.IOrderRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

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
    public Order getOrderById(final String id) {
        return orderRepository.getOrderById(id);
    }

    @Override
    public Set<Order> getOrdersByIds(final Set<String> orderIds) {
        return orderIds.stream().map(this::getOrderById).collect(Collectors.toSet());
    }
}
