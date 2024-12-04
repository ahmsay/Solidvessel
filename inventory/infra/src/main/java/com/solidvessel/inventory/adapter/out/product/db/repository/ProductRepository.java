package com.solidvessel.inventory.adapter.out.product.db.repository;

import com.solidvessel.inventory.adapter.out.product.db.entity.ProductJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends JpaRepository<ProductJpaEntity, Long>, PagingAndSortingRepository<ProductJpaEntity, Long> {
}
