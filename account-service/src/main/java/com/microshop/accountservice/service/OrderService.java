package com.microshop.accountservice.service;

import com.microshop.accountservice.response.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("order-s")
public interface OrderService {

    @GetMapping("/orders/ofCustomer/{customerId}")
    List<OrderResponse> getByCustomerId(@PathVariable final Long customerId);
}
