package com.solidvessel.account.adapter.out.payment.rest;

import com.solidvessel.account.adapter.out.payment.rest.response.PaymentResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.List;

@Primary
@FeignClient(name = "paymentRestClient", url = "${payment.url}", fallback = PaymentRestClient.PaymentFallback.class)
public interface PaymentRestClient {

    @Cacheable(value = "paymentsOfCustomer.rest", key = "#customerId")
    @GetMapping("/ofCustomer/{customerId}")
    List<PaymentResponse> getByCustomerId(@PathVariable String customerId, @RequestHeader("authorization") String token);

    @Component
    class PaymentFallback implements PaymentRestClient {

        @Override
        public List<PaymentResponse> getByCustomerId(String customerId, String token) {
            return new ArrayList<>();
        }
    }
}
