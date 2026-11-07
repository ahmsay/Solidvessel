package com.solidvessel.inventory.adapter.out.product.db.repository;

import com.solidvessel.inventory.adapter.out.product.db.entity.ProductJpaEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductJpaEntity, Long>, PagingAndSortingRepository<ProductJpaEntity, Long> {

    @EntityGraph(attributePaths = "attributes")
    Optional<ProductJpaEntity> findWithAttributesById(Long id);

    @EntityGraph(attributePaths = "attributes")
    List<ProductJpaEntity> findWithAttributesByIdIn(List<Long> ids);
}
