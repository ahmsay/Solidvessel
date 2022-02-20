package com.microshop.order.port;

import com.microshop.order.response.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "customerPort", url = "${account.url}")
public interface CustomerPort {

    @GetMapping("/customers/{id}")
    CustomerResponse getById(@PathVariable Long id);
}
