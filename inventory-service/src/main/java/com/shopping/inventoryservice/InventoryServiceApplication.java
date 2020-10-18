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
			productRepository.save(new Product("Clipper", 2.5D, "Tool", 1L));
			productRepository.save(new Product("Laptop", 3D, "Electronics", 1L));
			productRepository.save(new Product("Phone", 5D, "Electronics", 1L));
			productRepository.save(new Product("Car", 200D, "Vehicle", 2L));
			productRepository.save(new Product("Spaceship", 500D, "Vehicle", 3L));
			productRepository.save(new Product("Apple", 499.99D, "Fruit", 3L));
			productRepository.save(new Product("Ice Pick", 53D, "Tool"));
			productRepository.save(new Product("Desk", 25D, "Furniture"));
		};
	}

	public static void main(final String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

}
