package com.microshop.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class InventoryApplication {

    public static void main(final String[] args) {
        SpringApplication.run(InventoryApplication.class, args);
    }
}
