package com.microshop.account.port;

import com.microshop.account.response.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(value = "orderPort", url = "${order.url}")
public interface OrderPort {

    @GetMapping("/orders/ofCustomer/{customerId}")
    List<OrderResponse> getByCustomerId(@PathVariable final Long customerId, @RequestHeader("Cookie") String session);
}
