package com.solidvessel.inventory.infra.configuration;

import com.solidvessel.inventory.infra.adapter.product.db.entity.ProductJpaEntity;
import com.solidvessel.inventory.infra.adapter.product.db.repository.ProductRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

    @Bean
    InitializingBean seedDatabase(final ProductRepository productRepository) {
        return () -> {
            productRepository.save(new ProductJpaEntity("Clipper", 2.5D, "Tool", 1L));
            productRepository.save(new ProductJpaEntity("Laptop", 3D, "Electronics", 1L));
            productRepository.save(new ProductJpaEntity("Phone", 5D, "Electronics", 1L));
            productRepository.save(new ProductJpaEntity("Car", 200D, "Vehicle", 2L));
            productRepository.save(new ProductJpaEntity("Spaceship", 500D, "Vehicle", 3L));
            productRepository.save(new ProductJpaEntity("Apple", 499.99D, "Fruit", 3L));
            productRepository.save(new ProductJpaEntity("Ice Pick", 53D, "Tool", null));
            productRepository.save(new ProductJpaEntity("Desk", 25D, "Furniture", null));
        };
    }
}
