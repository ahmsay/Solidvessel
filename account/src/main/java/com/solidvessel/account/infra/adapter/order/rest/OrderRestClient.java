package com.solidvessel.account.infra.adapter.order.rest;

import com.solidvessel.account.domain.order.datamodel.OrderDataModel;
import com.solidvessel.shared.infra.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(value = "orderRestClient", url = "${order.url}")
public interface OrderRestClient {

    @GetMapping("/orders/ofCustomer/{customerId}")
    Response<List<OrderDataModel>> getByCustomerId(@PathVariable final Long customerId, @RequestHeader("Cookie") String session);
}
