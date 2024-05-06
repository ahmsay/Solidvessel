package com.solidvessel.order.adapter.in.order.rest;

import com.solidvessel.order.adapter.in.order.rest.response.OrderDetailResponse;
import com.solidvessel.order.adapter.in.order.rest.response.OrderResponse;
import com.solidvessel.order.adapter.out.customer.rest.response.CustomerResponse;
import com.solidvessel.order.adapter.out.payment.rest.PaymentRestClient;
import com.solidvessel.order.adapter.out.payment.rest.response.PaymentResponse;
import com.solidvessel.order.order.model.Order;
import com.solidvessel.order.order.port.OrderQueryPort;
import com.solidvessel.shared.idp.KeycloakAdapter;
import com.solidvessel.shared.query.QueryOptions;
import com.solidvessel.shared.security.SessionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderQueryPort orderQueryPort;
    private final KeycloakAdapter keycloakAdapter;
    private final PaymentRestClient paymentRestClient;

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/")
    public List<OrderResponse> getOrders(@RequestParam Integer pageNumber, @RequestParam(required = false) Integer pageSize) {
        return orderQueryPort.getOrders(QueryOptions.of(pageNumber, pageSize)).stream().map(OrderResponse::from).toList();
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/{id}")
    public OrderResponse getById(@PathVariable final Long id) {
        return OrderResponse.from(orderQueryPort.getById(id));
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/{id}/detail")
    public OrderDetailResponse getDetailById(@PathVariable final Long id) {
        Order order = orderQueryPort.getById(id);
        CustomerResponse customer = CustomerResponse.from(keycloakAdapter.getUser(order.getCustomerId()));
        PaymentResponse payment = paymentRestClient.getById(order.getPaymentId(), SessionUtil.getCurrentUserToken());
        return OrderDetailResponse.from(order, customer, payment);
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/ofCustomer/{customerId}")
    public List<OrderResponse> getByCustomerId(@PathVariable final String customerId) {
        return orderQueryPort.getByCustomerId(customerId).stream().map(OrderResponse::from).toList();
    }
}
