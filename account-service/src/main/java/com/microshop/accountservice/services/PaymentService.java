package com.microshop.accountservice.services;

import com.microshop.accountservice.dto.PaymentDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("payment-s")
public interface PaymentService {

    @GetMapping("/payments/ofCustomer/{customerId}")
    List<PaymentDTO> findByCustomerId(@PathVariable final Long customerId);
}
