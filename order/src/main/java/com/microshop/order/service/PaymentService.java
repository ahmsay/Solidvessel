package com.microshop.order.service;

import com.microshop.order.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "paymentService", url = "${payment.url}")
public interface PaymentService {

    @GetMapping("/payments/{id}")
    PaymentResponse getById(@PathVariable Long id);
}
