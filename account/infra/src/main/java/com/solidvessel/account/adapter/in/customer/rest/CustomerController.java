package com.solidvessel.account.adapter.in.customer.rest;

import com.solidvessel.account.adapter.in.customer.rest.response.CustomerDetailResponse;
import com.solidvessel.account.adapter.in.customer.rest.response.CustomerResponse;
import com.solidvessel.account.adapter.out.order.rest.OrderRestClient;
import com.solidvessel.account.adapter.out.order.rest.response.OrderResponse;
import com.solidvessel.account.adapter.out.payment.rest.PaymentRestClient;
import com.solidvessel.account.adapter.out.payment.rest.response.PaymentResponse;
import com.solidvessel.account.customer.model.Customer;
import com.solidvessel.account.customer.port.CustomerQueryPort;
import com.solidvessel.shared.security.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerQueryPort customerQueryPort;
    private final OrderRestClient orderRestClient;
    private final PaymentRestClient paymentRestClient;

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping()
    public List<CustomerResponse> getAll() {
        return customerQueryPort.getAll().stream().map(CustomerResponse::from).toList();
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/{id}")
    public CustomerResponse getById(@PathVariable final String id) {
        return CustomerResponse.from(customerQueryPort.getById(id));
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/{id}/detail")
    public CustomerDetailResponse getDetailById(@PathVariable final String id) {
        String token = SessionUtil.getCurrentUserToken();
        Customer customer = customerQueryPort.getById(id);
        List<OrderResponse> orders = orderRestClient.getByCustomerId(id, token);
        List<PaymentResponse> payments = paymentRestClient.getByCustomerId(id, token);
        return CustomerDetailResponse.from(customer, orders, payments);
    }
}
