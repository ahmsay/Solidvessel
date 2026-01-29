package com.solidvessel.inventory.adapter.out.product.db;

import com.solidvessel.inventory.adapter.out.product.db.entity.ProductJpaEntity;
import com.solidvessel.inventory.adapter.out.product.db.mapper.ProductJpaMapper;
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
    private final ProductJpaMapper productJpaMapper;

    @CacheEvict(value = "product", key = "#product.id")
    @Override
    public Product save(Product product) {
        ProductJpaEntity productJpaEntity = productRepository.save(productJpaMapper.toJpaEntity(product));
        return productJpaMapper.toDomainModel(productJpaEntity);
    }

    @Override
    public void saveProducts(List<Product> products) {
        List<ProductJpaEntity> productJpaEntities = new ArrayList<>();
        products.forEach(product -> productJpaEntities.add(productJpaMapper.toJpaEntity(product)));
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
