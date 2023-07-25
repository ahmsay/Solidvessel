package com.solidvessel.order.infra.adapter.out.customer.rest;

import com.solidvessel.order.domain.customer.datamodel.CustomerDataModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "customerRestClient", url = "${account.url}")
public interface CustomerRestClient {

    @GetMapping("/customers/{id}")
    CustomerDataModel getById(@PathVariable Long id, @RequestHeader("Cookie") String session);
}
