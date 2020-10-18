package com.shopping.orderservice.services;

import com.shopping.orderservice.entity.Order;
import com.shopping.orderservice.repositories.IOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    private final IOrderRepository orderRepository;

    public OrderService(final IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(final Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> getOrdersOfCustomer(final Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }
}
