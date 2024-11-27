package com.solidvessel.account.adapter.out.order.rest;

import com.solidvessel.account.adapter.out.order.rest.response.OrderResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.List;

@Primary
@FeignClient(name = "orderRestClient", url = "${order.url}", fallback = OrderRestClient.OrderFallback.class)
public interface OrderRestClient {

    @Cacheable(value = "ordersOfCustomer.rest", key = "#customerId")
    @GetMapping("/ofCustomer/{customerId}")
    List<OrderResponse> getByCustomerId(@PathVariable String customerId, @RequestHeader("authorization") String token);

    @Component
    class OrderFallback implements OrderRestClient {

        @Override
        public List<OrderResponse> getByCustomerId(String customerId, String token) {
            return new ArrayList<>();
        }
    }
}
