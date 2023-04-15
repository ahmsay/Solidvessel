package com.solidvessel.order.infra.adapter.order.db;

import com.solidvessel.order.domain.order.datamodel.OrderDataModel;
import com.solidvessel.order.domain.order.model.Order;
import com.solidvessel.order.domain.order.port.OrderPort;
import com.solidvessel.order.infra.adapter.order.db.entity.OrderJpaEntity;
import com.solidvessel.order.infra.adapter.order.db.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderDBAdapter implements OrderPort {

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

    @Override
    public void save(Order order) {
        orderRepository.save(OrderJpaEntity.from(order));
    }
}
