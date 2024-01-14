package com.solidvessel.order.adapter.out.payment.rest;

import com.solidvessel.order.adapter.out.payment.rest.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "paymentRestClient", url = "${payment.url}")
public interface PaymentRestClient {

    @GetMapping("/{id}")
    PaymentResponse getById(@PathVariable Long id, @RequestHeader("authorization") String session);
}
