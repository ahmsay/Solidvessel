package com.microshop.orderservice.services;

import com.microshop.orderservice.dto.CustomerDTO;
import com.microshop.orderservice.dto.OrderDTO;
import com.microshop.orderservice.dto.PaymentDTO;
import com.microshop.orderservice.entity.Order;
import com.microshop.orderservice.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final PaymentService paymentService;

    public OrderService(final OrderRepository orderRepository, final CustomerService customerService, final PaymentService paymentService) {
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.paymentService = paymentService;
    }

    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    public OrderDTO findById(final Long id) {
        Order order = findPrunedById(id);
        CustomerDTO customer = customerService.findById(order.getCustomerId());
        PaymentDTO payment = paymentService.findById(order.getPaymentId());
        return new OrderDTO(order.getId(), order.getStatus(), customer, payment);
    }

    public Order findPrunedById(final Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found!"));
    }

    public Iterable<Order> findByCustomerId(final Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public Order save(final Order order) {
        return orderRepository.save(order);
    }
}
