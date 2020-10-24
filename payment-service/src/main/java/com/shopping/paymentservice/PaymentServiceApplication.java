package com.shopping.paymentservice;

import com.shopping.paymentservice.entity.Payment;
import com.shopping.paymentservice.repositories.IPaymentRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class PaymentServiceApplication {

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	InitializingBean seedDatabase(final IPaymentRepository paymentRepository) {
		return () -> {
			paymentRepository.save(new Payment(10.5D, 1L));
			paymentRepository.save(new Payment(200D, 2L));
			paymentRepository.save(new Payment(999.99D, 2L));
		};
	}

	public static void main(final String[] args) {
		SpringApplication.run(PaymentServiceApplication.class, args);
	}

}
