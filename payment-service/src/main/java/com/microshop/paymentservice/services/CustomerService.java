package com.microshop.paymentservice.services;

import com.microshop.paymentservice.dto.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("account-s")
public interface CustomerService {

    @GetMapping("/customers/{id}/pruned")
    CustomerDTO findById(@PathVariable Long id);
}
