package com.shopping.orderservice.services;

import com.shopping.orderservice.entity.Order;
import com.shopping.orderservice.repositories.IOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService implements IOrderService {

    private final IOrderRepository orderRepository;

    public OrderService(final IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findById(final Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Order> findByCustomerId(final Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public Order save(final Order order) {
        return orderRepository.save(order);
    }
}
