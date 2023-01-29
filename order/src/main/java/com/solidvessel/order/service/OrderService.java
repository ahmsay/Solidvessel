package com.solidvessel.order.service;

import com.solidvessel.order.entity.CustomerOrder;
import com.solidvessel.order.repository.OrderRepository;
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

    public List<CustomerOrder> getAll() {
        return orderRepository.findAll();
    }

    public CustomerOrder getById(final Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found!"));
    }

    public List<CustomerOrder> getByCustomerId(final Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public CustomerOrder add(final CustomerOrder order) {
        return orderRepository.save(order);
    }
}
