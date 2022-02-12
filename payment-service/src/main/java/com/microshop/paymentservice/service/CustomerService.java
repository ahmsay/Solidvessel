package com.microshop.paymentservice.service;

import com.microshop.paymentservice.response.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("account-s")
public interface CustomerService {

    @GetMapping("/customers/{id}")
    CustomerResponse getById(@PathVariable Long id);
}
