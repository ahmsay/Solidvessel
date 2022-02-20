package com.microshop.order.port;

import com.microshop.order.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "paymentPort", url = "${payment.url}")
public interface PaymentPort {

    @GetMapping("/payments/{id}")
    PaymentResponse getById(@PathVariable Long id);
}