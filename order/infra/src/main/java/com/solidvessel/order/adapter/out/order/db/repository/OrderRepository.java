package com.solidvessel.order.adapter.out.order.db.repository;

import com.solidvessel.order.adapter.out.order.db.entity.OrderJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderJpaEntity, Long>, PagingAndSortingRepository<OrderJpaEntity, Long> {

    List<OrderJpaEntity> findByCustomerId(String customerId);

    Optional<OrderJpaEntity> findByIdAndCustomerId(Long id, String customerId);
}
