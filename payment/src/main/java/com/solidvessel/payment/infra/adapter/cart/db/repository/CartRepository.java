package com.solidvessel.payment.infra.adapter.cart.db.repository;

import com.solidvessel.payment.infra.adapter.cart.db.entity.CartJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartJpaEntity, Long> {
}
