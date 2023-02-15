package com.solidvessel.order.infra.adapter.customer.rest;

import com.solidvessel.order.domain.customer.datamodel.CustomerDataModel;
import com.solidvessel.shared.infra.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "customerRestClient", url = "${account.url}")
public interface CustomerRestClient {

    @GetMapping("/customers/{id}")
    Response<CustomerDataModel> getById(@PathVariable Long id, @RequestHeader("Cookie") String session);
}
