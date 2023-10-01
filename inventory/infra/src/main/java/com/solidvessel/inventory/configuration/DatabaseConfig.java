package com.solidvessel.inventory.configuration;

import com.solidvessel.inventory.adapter.out.product.db.entity.ProductJpaEntity;
import com.solidvessel.inventory.adapter.out.product.db.repository.ProductRepository;
import com.solidvessel.inventory.product.model.ProductCategory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Bean
    InitializingBean seedDatabase(final ProductRepository productRepository) {
        return () -> {
            productRepository.save(new ProductJpaEntity(1L, "Clipper", 2.5D, ProductCategory.TOOL, 1));
            productRepository.save(new ProductJpaEntity(2L, "Laptop", 3000D, ProductCategory.ELECTRONICS, 5));
            productRepository.save(new ProductJpaEntity(3L, "Phone", 5D, ProductCategory.ELECTRONICS, 6));
            productRepository.save(new ProductJpaEntity(4L, "Skirt", 20D, ProductCategory.CLOTHING, 3));
            productRepository.save(new ProductJpaEntity(5L, "Pants", 500D, ProductCategory.CLOTHING, 7));
            productRepository.save(new ProductJpaEntity(6L, "Apple", 499.99D, ProductCategory.ELECTRONICS, 4));
            productRepository.save(new ProductJpaEntity(7L, "Ice Pick", 53D, ProductCategory.TOOL, 12));
            productRepository.save(new ProductJpaEntity(8L, "Desk", 25D, ProductCategory.FURNITURE, 3));
        };
    }
}
