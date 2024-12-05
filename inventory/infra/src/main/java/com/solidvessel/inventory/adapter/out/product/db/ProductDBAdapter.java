package com.solidvessel.inventory.adapter.out.product.db;

import com.solidvessel.inventory.adapter.out.product.db.entity.ProductJpaEntity;
import com.solidvessel.inventory.adapter.out.product.db.repository.ProductRepository;
import com.solidvessel.inventory.product.model.Product;
import com.solidvessel.inventory.product.port.ProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductDBAdapter implements ProductPort {

    private final ProductRepository productRepository;

    @CacheEvict(value = "product", key = "#product.id")
    @Override
    public Product save(Product product) {
        return productRepository.save(ProductJpaEntity.from(product)).toDomainModel();
    }

    @Override
    public void saveProducts(List<Product> products) {
        List<ProductJpaEntity> productJpaEntities = new ArrayList<>();
        products.forEach(product -> productJpaEntities.add(ProductJpaEntity.from(product)));
        productRepository.saveAll(productJpaEntities);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        productRepository.deleteAllById(ids);
    }
}
