package com.solidvessel.payment.adapter.out.product.rest;

import com.solidvessel.payment.adapter.out.product.rest.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@FeignClient(name = "productPort", url = "${inventory.url}")
public interface ProductRestClient {

    @GetMapping("/product/ids")
    List<ProductResponse> getByIds(@RequestParam final Set<Long> ids, @RequestHeader("authorization") String token);

    @GetMapping("/product/isAvailable")
    boolean isAvailable(@RequestParam Long id, @RequestParam int quantity, @RequestHeader("authorization") String token);
}
