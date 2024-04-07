package com.solidvessel.account.adapter.in.customer.rest;

import com.solidvessel.account.adapter.in.customer.rest.response.CustomerDetailResponse;
import com.solidvessel.account.adapter.in.customer.rest.response.CustomerResponse;
import com.solidvessel.account.adapter.out.order.rest.OrderRestClient;
import com.solidvessel.account.adapter.out.order.rest.response.OrderResponse;
import com.solidvessel.account.adapter.out.payment.rest.PaymentRestClient;
import com.solidvessel.account.adapter.out.payment.rest.response.PaymentResponse;
import com.solidvessel.shared.security.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private final RealmResource keycloakRealm;
    private final OrderRestClient orderRestClient;
    private final PaymentRestClient paymentRestClient;

    @Cacheable(value = "customers")
    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping()
    public List<CustomerResponse> getUsers(@RequestParam Integer start, @RequestParam(required = false) Integer end) {
        List<UserRepresentation> users = keycloakRealm.users().list(start, end);
        return users.stream().map(CustomerResponse::from).toList();
    }

    @Cacheable(value = "customer", key = "#id")
    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/{id}")
    public CustomerResponse getById(@PathVariable final String id) {
        return CustomerResponse.from(keycloakRealm.users().get(id).toRepresentation());
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/{id}/detail")
    public CustomerDetailResponse getDetailById(@PathVariable final String id) {
        String token = SessionUtil.getCurrentUserToken();
        CustomerResponse customer = getById(id);
        List<OrderResponse> orders = orderRestClient.getByCustomerId(id, token);
        List<PaymentResponse> payments = paymentRestClient.getByCustomerId(id, token);
        return CustomerDetailResponse.from(customer, orders, payments);
    }
}
