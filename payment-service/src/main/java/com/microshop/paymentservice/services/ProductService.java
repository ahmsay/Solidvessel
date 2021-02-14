package com.microshop.paymentservice.services;

import com.microshop.paymentservice.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("inventory-s")
public interface ProductService {

    @GetMapping("/products/findByIds")
    List<ProductDTO> findByIds(@RequestParam final List<Long> ids);
}
