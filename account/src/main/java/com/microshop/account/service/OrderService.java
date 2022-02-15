package com.microshop.account.service;

import com.microshop.account.response.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "orderService", url = "${order.url}")
public interface OrderService {

    @GetMapping("/orders/ofCustomer/{customerId}")
    List<OrderResponse> getByCustomerId(@PathVariable final Long customerId);
}
