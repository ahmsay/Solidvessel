package com.shopping.orderservice;

import com.shopping.orderservice.entity.Order;
import com.shopping.orderservice.repositories.IOrderRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class OrderServiceApplication {

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	InitializingBean seedDatabase(IOrderRepository orderRepository) {
		return () -> {
			orderRepository.save(new Order("Delivered", 1L, 1L));
			orderRepository.save(new Order("On the way", 2L, 2L));
			orderRepository.save(new Order("Crashed", 2L, 3L));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}
