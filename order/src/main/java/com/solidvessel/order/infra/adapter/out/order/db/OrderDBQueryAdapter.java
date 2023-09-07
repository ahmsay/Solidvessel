package com.solidvessel.order.infra.adapter.out.order.db;

import com.solidvessel.order.domain.order.datamodel.OrderDataModel;
import com.solidvessel.order.domain.order.port.OrderQueryPort;
import com.solidvessel.order.infra.adapter.out.order.db.entity.OrderJpaEntity;
import com.solidvessel.order.infra.adapter.out.order.db.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderDBQueryAdapter implements OrderQueryPort {

    private final OrderRepository orderRepository;

    @Override
    public List<OrderDataModel> getAll() {
        return orderRepository.findAll().stream().map(OrderJpaEntity::toDataModel).toList();
    }

    @Override
    public OrderDataModel getById(Long id) {
        OrderJpaEntity orderJpaEntity = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found!"));
        return orderJpaEntity.toDataModel();
    }

    @Override
    public List<OrderDataModel> getByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId).stream().map(OrderJpaEntity::toDataModel).toList();
    }
}
