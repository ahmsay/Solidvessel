package com.microshop.inventoryservice;

import com.microshop.inventoryservice.entity.Product;
import com.microshop.inventoryservice.repositories.IProductRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

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
			productRepository.save(new Product("Ice Pick", 53D, "Tool", null));
			productRepository.save(new Product("Desk", 25D, "Furniture", null));
		};
	}

	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		// Don't do this in production, use a proper list  of allowed origins
		config.setAllowedOrigins(Collections.singletonList("*"));
		config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept"));
		config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

	public static void main(final String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

}
