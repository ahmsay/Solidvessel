package com.microshop.account.service;

import com.microshop.account.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("payment-s")
public interface PaymentService {

    @GetMapping("/payments/ofCustomer/{customerId}")
    List<PaymentResponse> getByCustomerId(@PathVariable final Long customerId);
}
