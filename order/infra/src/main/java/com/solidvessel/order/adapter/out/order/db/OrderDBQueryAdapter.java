package com.solidvessel.order.adapter.out.order.db;

import com.solidvessel.order.adapter.out.order.db.entity.OrderJpaEntity;
import com.solidvessel.order.adapter.out.order.db.repository.OrderRepository;
import com.solidvessel.order.order.model.Order;
import com.solidvessel.order.order.port.OrderQueryPort;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderDBQueryAdapter implements OrderQueryPort {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll().stream().map(OrderJpaEntity::toDomainModel).toList();
    }

    @Override
    public Order getById(Long id) {
        OrderJpaEntity orderJpaEntity = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found!"));
        return orderJpaEntity.toDomainModel();
    }

    @Override
    public List<Order> getByCustomerId(String customerId) {
        return orderRepository.findByCustomerId(customerId).stream().map(OrderJpaEntity::toDomainModel).toList();
    }
}
