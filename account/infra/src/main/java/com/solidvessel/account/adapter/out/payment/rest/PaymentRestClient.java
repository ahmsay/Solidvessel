package com.solidvessel.account.adapter.out.payment.rest;

import com.solidvessel.account.adapter.out.payment.rest.response.PaymentResponse;
import com.solidvessel.shared.Profiles;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Profile("!" + Profiles.INTEGRATION_TEST)
@FeignClient(value = "paymentRestClient", url = "${payment.url}")
public interface PaymentRestClient {

    @GetMapping("/ofCustomer/{customerId}")
    List<PaymentResponse> getByCustomerId(@PathVariable final String customerId, @RequestHeader("authorization") String token);
}
