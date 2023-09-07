package com.solidvessel.inventory.infra.adapter.out.product.db;

import com.solidvessel.inventory.domain.product.model.Product;
import com.solidvessel.inventory.domain.product.port.ProductPort;
import com.solidvessel.inventory.infra.adapter.out.product.db.entity.ProductJpaEntity;
import com.solidvessel.inventory.infra.adapter.out.product.db.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductDBAdapter implements ProductPort {

    private final ProductRepository productRepository;

    @Override
    public void save(Product product) {
        productRepository.save(ProductJpaEntity.from(product));
    }

    @Override
    public void saveProducts(List<Product> products) {
        List<ProductJpaEntity> productJpaEntities = new ArrayList<>();
        products.forEach(product -> productJpaEntities.add(ProductJpaEntity.from(product)));
        productRepository.saveAll(productJpaEntities);
    }
}
