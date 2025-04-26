package com.solidvessel.account.adapter.in.customer.rest;

import com.solidvessel.account.adapter.in.customer.rest.response.CustomerDetailResponse;
import com.solidvessel.account.adapter.in.customer.rest.response.CustomerResponse;
import com.solidvessel.account.adapter.out.order.rest.OrderRestClient;
import com.solidvessel.account.adapter.out.order.rest.response.OrderResponse;
import com.solidvessel.account.adapter.out.payment.rest.PaymentRestClient;
import com.solidvessel.account.adapter.out.payment.rest.response.PaymentResponse;
import com.solidvessel.shared.idp.KeycloakAdapter;
import com.solidvessel.shared.security.SessionUtil;
import com.solidvessel.shared.service.OperationResult;
import com.solidvessel.shared.service.ResultType;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final KeycloakAdapter keycloakAdapter;
    private final OrderRestClient orderRestClient;
    private final PaymentRestClient paymentRestClient;

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping
    public List<CustomerResponse> getCustomers(@RequestParam Integer start, @RequestParam(required = false) Integer end) {
        List<UserRepresentation> users = keycloakAdapter.getUsers(start, end);
        return users.stream().map(CustomerResponse::from).toList();
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/{id}")
    public CustomerResponse getById(@PathVariable String id) {
        return CustomerResponse.from(keycloakAdapter.getUser(id));
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/{id}/detail")
    public CustomerDetailResponse getDetailById(@PathVariable String id) {
        String token = SessionUtil.getCurrentUserToken();
        CustomerResponse customer = getById(id);
        List<OrderResponse> orders = orderRestClient.getByCustomerId(id, token);
        List<PaymentResponse> payments = paymentRestClient.getByCustomerId(id, token);
        return CustomerDetailResponse.from(customer, orders, payments);
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PutMapping("/{id}/activate")
    public OperationResult activateCustomer(@PathVariable String id) {
        keycloakAdapter.activateUser(id);
        return new OperationResult("Customer %s is activated.".formatted(id), ResultType.SUCCESS);
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PutMapping("/{id}/deactivate")
    public OperationResult deactivateCustomer(@PathVariable String id) {
        keycloakAdapter.deactivateUser(id);
        return new OperationResult("Customer %s is deactivated.".formatted(id), ResultType.SUCCESS);
    }
}
