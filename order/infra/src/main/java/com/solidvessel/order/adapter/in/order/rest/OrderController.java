package com.solidvessel.order.adapter.in.order.rest;

import com.solidvessel.order.adapter.in.order.rest.datamodel.OrderDataModel;
import com.solidvessel.order.adapter.in.order.rest.datamodel.OrderDetailDataModel;
import com.solidvessel.order.customer.model.Customer;
import com.solidvessel.order.customer.port.CustomerQueryPort;
import com.solidvessel.order.order.model.Order;
import com.solidvessel.order.order.port.OrderQueryPort;
import com.solidvessel.order.payment.model.Payment;
import com.solidvessel.order.payment.port.PaymentQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderQueryPort orderQueryPort;
    private final CustomerQueryPort customerQueryPort;
    private final PaymentQueryPort paymentQueryPort;

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/")
    public List<OrderDataModel> getAll() {
        return orderQueryPort.getAll().stream().map(OrderDataModel::from).toList();
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/{id}")
    public OrderDataModel getById(@PathVariable final Long id) {
        return OrderDataModel.from(orderQueryPort.getById(id));
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/{id}/detail")
    public OrderDetailDataModel getDetailById(@PathVariable final Long id) {
        Order order = orderQueryPort.getById(id);
        Customer customer = customerQueryPort.getCustomerOfOrder(order.getCustomerId());
        Payment payment = paymentQueryPort.getPaymentOfOrder(order.getPaymentId());
        return OrderDetailDataModel.from(order, customer, payment);
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/ofCustomer/{customerId}")
    public List<OrderDataModel> getByCustomerId(@PathVariable final String customerId) {
        return orderQueryPort.getByCustomerId(customerId).stream().map(OrderDataModel::from).toList();
    }
}
