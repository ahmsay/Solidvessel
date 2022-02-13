package com.microshop.inventoryservice.configuration;

import com.microshop.inventoryservice.entity.Product;
import com.microshop.inventoryservice.repository.ProductRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {

    @Bean
    InitializingBean seedDatabase(final ProductRepository productRepository) {
        return () -> {
            productRepository.save(new Product("Clipper", 2.5D, "Tool", 1L));
            productRepository.save(new Product("Laptop", 3D, "Electronics", 1L));
            productRepository.save(new Product("Phone", 5D, "Electronics", 1L));
            productRepository.save(new Product("Car", 200D, "Vehicle", 2L));
            productRepository.save(new Product("Spaceship", 500D, "Vehicle", 3L));
            productRepository.save(new Product("Apple", 499.99D, "Fruit", 3L));
            productRepository.save(new Product("Ice Pick", 53D, "Tool", null));
            productRepository.save(new Product("Desk", 25D, "Furniture", null));
        };
    }
}
