package com.microshop.accountservice;

import com.microshop.accountservice.entity.Customer;
import com.microshop.accountservice.repositories.ICustomerRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class AccountServiceApplication {

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	InitializingBean seedDatabase(final ICustomerRepository customerRepository) {
		return () -> {
			customerRepository.save(new Customer("Zorkov"));
			customerRepository.save(new Customer("Lorne"));
			customerRepository.save(new Customer("Matthias"));
		};
	}

	public static void main(final String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}

}
