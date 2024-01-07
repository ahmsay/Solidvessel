package com.solidvessel.account.adapter.out.order.rest;

import com.solidvessel.account.adapter.out.order.rest.datamodel.OrderDataModel;
import com.solidvessel.shared.Profiles;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Profile("!" + Profiles.INTEGRATION_TEST)
@FeignClient(value = "orderRestClient", url = "${order.url}")
public interface OrderRestClient {

    @GetMapping("/ofCustomer/{customerId}")
    List<OrderDataModel> getByCustomerId(@PathVariable final String customerId, @RequestHeader("authorization") String token);
}
