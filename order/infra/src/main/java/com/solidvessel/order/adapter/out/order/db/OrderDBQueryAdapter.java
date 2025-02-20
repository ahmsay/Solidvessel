package com.solidvessel.order.adapter.out.order.db;

import com.solidvessel.order.adapter.out.order.db.entity.OrderJpaEntity;
import com.solidvessel.order.adapter.out.order.db.repository.OrderRepository;
import com.solidvessel.order.order.model.Order;
import com.solidvessel.order.order.port.OrderQueryPort;
import com.solidvessel.shared.query.QueryOptions;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.solidvessel.shared.jpa.query.PageUtil.withPage;

@Repository
@RequiredArgsConstructor
public class OrderDBQueryAdapter implements OrderQueryPort {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> getOrders(QueryOptions queryOptions) {
        return orderRepository.findAll(withPage(queryOptions)).stream().map(OrderJpaEntity::toDomainModel).toList();
    }

    @Cacheable(value = "order", key = "#id")
    @Override
    public Order getById(Long id) {
        OrderJpaEntity orderJpaEntity = orderRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Order not found!"));
        return orderJpaEntity.toDomainModel();
    }

    @Cacheable(value = "ordersOfCustomer", key = "#customerId")
    @Override
    public List<Order> getByCustomerId(String customerId) {
        return orderRepository.findByCustomerId(customerId).stream().map(OrderJpaEntity::toDomainModel).toList();
    }

    @Override
    public Order getByIdAndCustomerId(Long id, String customerId) {
        OrderJpaEntity orderJpaEntity = orderRepository.findByIdAndCustomerId(id, customerId).orElseThrow(() -> new EntityNotFoundException("Order not found!"));
        return orderJpaEntity.toDomainModel();
    }
}
