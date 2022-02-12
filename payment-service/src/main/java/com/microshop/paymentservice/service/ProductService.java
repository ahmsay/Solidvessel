package com.microshop.paymentservice.service;

import com.microshop.paymentservice.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("inventory-s")
public interface ProductService {

    @GetMapping("/products/getByIds")
    List<ProductResponse> getByIds(@RequestParam final List<Long> ids);
}
