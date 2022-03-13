package com.microshop.payment.port;

import com.microshop.payment.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "productPort", url = "${inventory.url}")
public interface ProductPort {

    @GetMapping("/products/ofPayment/{paymentId}")
    List<ProductResponse> getByPaymentId(@PathVariable final Long paymentId, @RequestHeader("Cookie") String session);
}
