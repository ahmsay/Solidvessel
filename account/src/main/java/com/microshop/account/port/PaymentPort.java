package com.microshop.account.port;

import com.microshop.account.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(value = "paymentPort", url = "${payment.url}")
public interface PaymentPort {

    @GetMapping("/payments/ofCustomer/{customerId}")
    List<PaymentResponse> getByCustomerId(@PathVariable final Long customerId, @RequestHeader("Cookie") String session);
}
