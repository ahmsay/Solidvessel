package com.solidvessel.account.adapter.out.order.rest;

import com.solidvessel.account.order.datamodel.OrderDataModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(value = "orderRestClient", url = "${order.url}")
public interface OrderRestClient {

    @GetMapping("/ofCustomer/{customerId}")
    List<OrderDataModel> getByCustomerId(@PathVariable final Long customerId, @RequestHeader("Cookie") String session);
}
