package com.microshop.order.service;

import com.microshop.order.response.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "customerService", url = "${account.url}")
public interface CustomerService {

    @GetMapping("/customers/{id}")
    CustomerResponse getById(@PathVariable Long id);
}
