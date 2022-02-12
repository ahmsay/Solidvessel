package com.microshop.orderservice.service;

import com.microshop.orderservice.response.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("account-s")
public interface CustomerService {

    @GetMapping("/customers/{id}")
    CustomerResponse getById(@PathVariable Long id);
}
