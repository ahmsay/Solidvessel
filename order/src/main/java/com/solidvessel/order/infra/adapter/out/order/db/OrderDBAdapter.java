package com.solidvessel.order.infra.adapter.out.order.db;

import com.solidvessel.order.domain.order.model.Order;
import com.solidvessel.order.domain.order.port.OrderPort;
import com.solidvessel.order.infra.adapter.out.order.db.entity.OrderJpaEntity;
import com.solidvessel.order.infra.adapter.out.order.db.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderDBAdapter implements OrderPort {

    private final OrderRepository orderRepository;

    @Override
    public void save(Order order) {
        orderRepository.save(OrderJpaEntity.from(order));
    }
}
