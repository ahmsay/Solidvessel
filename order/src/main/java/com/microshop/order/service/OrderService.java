package com.microshop.order.service;

import com.microshop.order.entity.Order;
import com.microshop.order.repository.OrderRepository;
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

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public Order getById(final Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found!"));
    }

    public List<Order> getByCustomerId(final Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public Order add(final Order order) {
        return orderRepository.save(order);
    }
}
