package com.solidvessel.order.adapter.out.order.db;

import com.solidvessel.order.adapter.out.order.db.entity.OrderJpaEntity;
import com.solidvessel.order.adapter.out.order.db.repository.OrderRepository;
import com.solidvessel.order.order.model.Order;
import com.solidvessel.order.order.port.OrderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderDBAdapter implements OrderPort {

    private final OrderRepository orderRepository;

    @Override
    public void save(Order order) {
        orderRepository.save(OrderJpaEntity.from(order));
    }
}
