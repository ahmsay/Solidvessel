package com.solidvessel.inventory.infra.configuration;

import com.solidvessel.inventory.domain.product.model.ProductCategory;
import com.solidvessel.inventory.infra.adapter.out.product.db.entity.ProductJpaEntity;
import com.solidvessel.inventory.infra.adapter.out.product.db.repository.ProductRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Bean
    InitializingBean seedDatabase(final ProductRepository productRepository) {
        return () -> {
            productRepository.save(new ProductJpaEntity(null, "Clipper", 2.5D, ProductCategory.TOOL, 1));
            productRepository.save(new ProductJpaEntity(null, "Laptop", 3000D, ProductCategory.ELECTRONICS, 5));
            productRepository.save(new ProductJpaEntity(null, "Phone", 5D, ProductCategory.ELECTRONICS, 6));
            productRepository.save(new ProductJpaEntity(null, "Skirt", 20D, ProductCategory.CLOTHING, 3));
            productRepository.save(new ProductJpaEntity(null, "Pants", 500D, ProductCategory.CLOTHING, 7));
            productRepository.save(new ProductJpaEntity(null, "Apple", 499.99D, ProductCategory.ELECTRONICS, 4));
            productRepository.save(new ProductJpaEntity(null, "Ice Pick", 53D, ProductCategory.TOOL, 12));
            productRepository.save(new ProductJpaEntity(null, "Desk", 25D, ProductCategory.FURNITURE, 3));
        };
    }
}
