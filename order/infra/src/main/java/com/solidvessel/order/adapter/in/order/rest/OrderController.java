package com.solidvessel.order.adapter.in.order.rest;

import com.solidvessel.order.adapter.in.order.rest.request.CancelOrderRequest;
import com.solidvessel.order.adapter.in.order.rest.request.DeliverOrderRequest;
import com.solidvessel.order.adapter.in.order.rest.request.UpdateDeliveryAddressRequest;
import com.solidvessel.order.adapter.in.order.rest.response.OrderDetailResponse;
import com.solidvessel.order.adapter.in.order.rest.response.OrderResponse;
import com.solidvessel.order.adapter.out.customer.rest.response.CustomerResponse;
import com.solidvessel.order.adapter.out.payment.rest.PaymentRestClient;
import com.solidvessel.order.adapter.out.payment.rest.response.PaymentResponse;
import com.solidvessel.order.order.model.Order;
import com.solidvessel.order.order.port.OrderQueryPort;
import com.solidvessel.order.order.service.CancelOrderCommandService;
import com.solidvessel.order.order.service.DeliverOrderCommandService;
import com.solidvessel.order.order.service.UpdateDeliveryAddressCommandService;
import com.solidvessel.shared.idp.KeycloakAdapter;
import com.solidvessel.shared.query.QueryOptions;
import com.solidvessel.shared.security.SessionUtil;
import com.solidvessel.shared.service.OperationResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderQueryPort orderQueryPort;
    private final KeycloakAdapter keycloakAdapter;
    private final PaymentRestClient paymentRestClient;
    private final CancelOrderCommandService cancelOrderCommandService;
    private final DeliverOrderCommandService deliverOrderCommandService;
    private final UpdateDeliveryAddressCommandService updateDeliveryAddressCommandService;

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/")
    public List<OrderResponse> getOrders(@RequestParam Integer pageNumber, @RequestParam(required = false) Integer pageSize) {
        return orderQueryPort.getOrders(QueryOptions.of(pageNumber, pageSize)).stream().map(OrderResponse::from).toList();
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/ofCurrentCustomer")
    public List<OrderResponse> getOrdersOfCurrentCustomer() {
        return orderQueryPort.getByCustomerId(SessionUtil.getCurrentUserId()).stream().map(OrderResponse::from).toList();
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/{id}")
    public OrderResponse getById(@PathVariable Long id) {
        return OrderResponse.from(orderQueryPort.getById(id));
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/{id}/detail")
    public OrderDetailResponse getDetailById(@PathVariable Long id) {
        Order order = orderQueryPort.getById(id);
        CustomerResponse customer = CustomerResponse.from(keycloakAdapter.getUser(order.getCustomerId()));
        PaymentResponse payment = paymentRestClient.getById(order.getPaymentId(), SessionUtil.getCurrentUserToken());
        return OrderDetailResponse.from(order, customer, payment);
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/ofCustomer/{customerId}")
    public List<OrderResponse> getByCustomerId(@PathVariable String customerId) {
        return orderQueryPort.getByCustomerId(customerId).stream().map(OrderResponse::from).toList();
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PutMapping("/{id}/cancel")
    public OperationResult cancelOrder(@PathVariable Long id, @RequestBody @Valid CancelOrderRequest request) {
        return cancelOrderCommandService.execute(request.toCommand(id));
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @PutMapping("/{id}/deliver")
    public OperationResult deliverOrder(@PathVariable Long id, @RequestBody @Valid DeliverOrderRequest request) {
        return deliverOrderCommandService.execute(request.toCommand(id));
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PutMapping("/{id}/deliveryAddress")
    public OperationResult updateDeliveryAddress(@PathVariable Long id, @RequestBody @Valid UpdateDeliveryAddressRequest request) {
        return updateDeliveryAddressCommandService.execute(request.toCommand(id));
    }
}
