package com.solidvessel.inventory.adapter.out.product.db.repository;

import com.solidvessel.inventory.adapter.out.product.db.entity.ProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductJpaEntity, Long>, PagingAndSortingRepository<ProductJpaEntity, Long> {

    Optional<ProductJpaEntity> findByIdAndQuantityGreaterThanEqual(Long id, int quantity);
}
