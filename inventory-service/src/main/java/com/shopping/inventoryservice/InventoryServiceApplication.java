package com.shopping.inventoryservice;

import com.shopping.inventoryservice.entity.Product;
import com.shopping.inventoryservice.repositories.IProductRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class InventoryServiceApplication {

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	InitializingBean seedDatabase(final IProductRepository productRepository) {
		return () -> {
			productRepository.save(new Product("Clipper", 2.5, "Tool", 1L));
			productRepository.save(new Product("Laptop", 3, "Electronics", 1L));
			productRepository.save(new Product("Phone", 5, "Electronics", 1L));
			productRepository.save(new Product("Car", 200, "Vehicle", 2L));
			productRepository.save(new Product("Spaceship", 500, "Vehicle", 3L));
			productRepository.save(new Product("Apple", 499.99, "Fruit", 3L));
			productRepository.save(new Product("Ice Pick", 53, "Tool", -1));
			productRepository.save(new Product("Desk", 25, "Furniture", -1));
		};
	}

	public static void main(final String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

}
