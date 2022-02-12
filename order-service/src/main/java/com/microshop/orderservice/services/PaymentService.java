package com.microshop.orderservice.services;

import com.microshop.orderservice.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("payment-s")
public interface PaymentService {

    @GetMapping("/payments/{id}/pruned")
    PaymentResponse findById(@PathVariable Long id);
}
