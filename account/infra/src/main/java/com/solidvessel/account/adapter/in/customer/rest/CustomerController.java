package com.solidvessel.account.adapter.in.customer.rest;

import com.solidvessel.account.adapter.in.customer.rest.datamodel.CustomerDataModel;
import com.solidvessel.account.adapter.in.customer.rest.datamodel.CustomerDetailDataModel;
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
    public List<CustomerDataModel> getAll() {
        return customerQueryPort.getAll().stream().map(CustomerDataModel::from).toList();
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/{id}")
    public CustomerDataModel getById(@PathVariable final String id) {
        return CustomerDataModel.from(customerQueryPort.getById(id));
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/{id}/detail")
    public CustomerDetailDataModel getDetailById(@PathVariable final String id) {
        Customer customer = customerQueryPort.getById(id);
        List<Order> orders = orderQueryPort.getOrdersOfCustomer(id);
        List<Payment> payments = paymentQueryPort.getPaymentsOfCustomer(id);
        return CustomerDetailDataModel.from(customer, orders, payments);
    }
}
