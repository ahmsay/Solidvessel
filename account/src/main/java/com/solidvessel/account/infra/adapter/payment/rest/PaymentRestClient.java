package com.solidvessel.account.infra.adapter.payment.rest;

import com.solidvessel.account.infra.adapter.payment.rest.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(value = "paymentRestClient", url = "${payment.url}")
public interface PaymentRestClient {

    @GetMapping("/payments/ofCustomer/{customerId}")
    List<PaymentResponse> getByCustomerId(@PathVariable final Long customerId, @RequestHeader("Cookie") String session);
}
