package com.solidvessel.payment.infra.adapter.product.rest;

import com.solidvessel.payment.domain.product.datamodel.ProductDataModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@FeignClient(name = "productPort", url = "${inventory.url}")
public interface ProductRestClient {

    @GetMapping("/products/ids")
    List<ProductDataModel> getByIds(@RequestParam final Set<Long> ids, @RequestHeader("Cookie") String session);

    @GetMapping("/products/available")
    boolean isAvailable(@RequestParam Long id, @RequestParam int quantity, @RequestHeader("Cookie") String session);
}
