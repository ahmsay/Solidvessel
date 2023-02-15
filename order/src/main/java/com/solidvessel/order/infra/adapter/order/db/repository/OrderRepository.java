package com.solidvessel.order.infra.adapter.order.db.repository;

import com.solidvessel.order.infra.adapter.order.db.entity.OrderJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderJpaEntity, Long> {

    List<OrderJpaEntity> findByCustomerId(Long customerId);
}
