package com.solidvessel.order.infra.adapter.payment.rest;

import com.solidvessel.order.domain.payment.datamodel.PaymentDataModel;
import com.solidvessel.shared.infra.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "paymentRestClient", url = "${payment.url}")
public interface PaymentRestClient {

    @GetMapping("/payments/{id}")
    Response<PaymentDataModel> getById(@PathVariable Long id, @RequestHeader("Cookie") String session);
}
