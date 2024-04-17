package com.solidvessel.order.adapter.out.payment.rest;

import com.solidvessel.order.adapter.out.payment.rest.response.PaymentResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@Primary
@FeignClient(name = "paymentRestClient", url = "${payment.url}", fallback = PaymentRestClient.PaymentFallback.class)
public interface PaymentRestClient {

    @Cacheable(value = "payment.rest", key = "#id")
    @GetMapping("/{id}")
    PaymentResponse getById(@PathVariable Long id, @RequestHeader("authorization") String token);

    @Component
    class PaymentFallback implements PaymentRestClient {

        @Override
        public PaymentResponse getById(Long id, String token) {
            return null;
        }
    }
}
