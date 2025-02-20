package com.solidvessel.order.adapter.out.order.db;

import com.solidvessel.order.adapter.out.order.db.entity.OrderJpaEntity;
import com.solidvessel.order.adapter.out.order.db.repository.OrderRepository;
import com.solidvessel.order.order.model.Order;
import com.solidvessel.order.order.port.OrderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderDBAdapter implements OrderPort {

    private final OrderRepository orderRepository;

    @Caching(evict = {
            @CacheEvict(value = "ordersOfCustomer", key = "#order.customerId"),
            @CacheEvict(value = "ordersOfCustomer.rest", key = "#order.customerId")
    })
    @Override
    public void save(Order order) {
        orderRepository.save(OrderJpaEntity.from(order));
    }
}
