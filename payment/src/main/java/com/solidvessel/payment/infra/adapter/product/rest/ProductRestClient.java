package com.solidvessel.payment.infra.adapter.product.rest;

import com.solidvessel.payment.domain.product.datamodel.ProductDataModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "productPort", url = "${inventory.url}")
public interface ProductRestClient {

    @GetMapping("/products/ofPayment/{paymentId}")
    List<ProductDataModel> getByPaymentId(@PathVariable final Long paymentId, @RequestHeader("Cookie") String session);

    @GetMapping("/products/{id}/available")
    boolean isAvailable(@PathVariable final Long id, @RequestHeader("Cookie") String session);
}
