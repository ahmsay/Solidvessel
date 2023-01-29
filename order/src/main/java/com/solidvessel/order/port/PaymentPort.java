package com.solidvessel.order.port;

import com.solidvessel.order.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "paymentPort", url = "${payment.url}")
public interface PaymentPort {

    @GetMapping("/payments/{id}")
    PaymentResponse getById(@PathVariable Long id, @RequestHeader("Cookie") String session);
}
