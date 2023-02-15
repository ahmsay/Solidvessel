package com.solidvessel.inventory.infra.adapter.product.db.repository;

import com.solidvessel.inventory.infra.adapter.product.db.entity.ProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductJpaEntity, Long> {

    List<ProductJpaEntity> findByPaymentId(final Long paymentId);
}
