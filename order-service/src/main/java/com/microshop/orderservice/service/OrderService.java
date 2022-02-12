package com.microshop.orderservice.service;

import com.microshop.orderservice.entity.Order;
import com.microshop.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(final Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found!"));
    }

    public List<Order> findByCustomerId(final Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public Order save(final Order order) {
        return orderRepository.save(order);
    }
}
