package com.solidvessel.account.adapter.in.customer.rest;

import com.solidvessel.account.adapter.in.customer.rest.response.CustomerDetailResponse;
import com.solidvessel.account.adapter.in.customer.rest.response.CustomerResponse;
import com.solidvessel.account.customer.model.Customer;
import com.solidvessel.account.customer.port.CustomerQueryPort;
import com.solidvessel.account.order.model.Order;
import com.solidvessel.account.order.port.OrderQueryPort;
import com.solidvessel.account.payment.model.Payment;
import com.solidvessel.account.payment.port.PaymentQueryPort;
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
    private final OrderQueryPort orderQueryPort;
    private final PaymentQueryPort paymentQueryPort;

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
        Customer customer = customerQueryPort.getById(id);
        List<Order> orders = orderQueryPort.getOrdersOfCustomer(id);
        List<Payment> payments = paymentQueryPort.getPaymentsOfCustomer(id);
        return CustomerDetailResponse.from(customer, orders, payments);
    }
}
