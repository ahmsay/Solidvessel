package com.solidvessel.order.adapter.out.payment.rest;

import com.solidvessel.order.payment.datamodel.PaymentDataModel;
import com.solidvessel.shared.Profiles;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@Profile("!" + Profiles.INTEGRATION_TEST)
@FeignClient(name = "paymentRestClient", url = "${payment.url}")
public interface PaymentRestClient {

    @GetMapping("/{id}")
    PaymentDataModel getById(@PathVariable Long id, @RequestHeader("authorization") String session);
}
