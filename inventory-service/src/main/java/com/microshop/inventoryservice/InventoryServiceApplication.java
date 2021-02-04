package com.microshop.inventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class InventoryServiceApplication {
	// TODO Config server integration and remote package may be removed in this microservice.
	public static void main(final String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
}
