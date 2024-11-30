package com.solidvessel.inventory.adapter.out.product.db;

import com.solidvessel.inventory.adapter.out.product.db.entity.ProductJpaEntity;
import com.solidvessel.inventory.adapter.out.product.db.repository.ProductRepository;
import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.inventory.product.port.ProductQueryPort;
import com.solidvessel.shared.query.QueryOptions;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.solidvessel.shared.jpa.query.PageUtil.withPage;

@Repository
@RequiredArgsConstructor
public class ProductDBQueryAdapter implements ProductQueryPort {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getProducts(QueryOptions queryOptions) {
        return productRepository.findAll(withPage(queryOptions)).stream().map(ProductJpaEntity::toDomainModel).toList();
    }

    @Cacheable(value = "product", key = "#id")
    @Override
    public Product getById(Long id) {
        ProductJpaEntity productJpaEntity = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found!"));
        return productJpaEntity.toDomainModel();
    }

    @Override
    public List<Product> getByIds(List<Long> ids) {
        return productRepository.findAllById(ids).stream().map(ProductJpaEntity::toDomainModel).toList();
    }
}
