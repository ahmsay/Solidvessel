package com.solidvessel.account.adapter.in.customer.rest;

import com.solidvessel.account.customer.datamodel.CustomerDataModel;
import com.solidvessel.account.customer.datamodel.CustomerDetailDataModel;
import com.solidvessel.account.customer.port.CustomerQueryPort;
import com.solidvessel.account.order.datamodel.OrderDataModel;
import com.solidvessel.account.order.port.OrderQueryPort;
import com.solidvessel.account.payment.datamodel.PaymentDataModel;
import com.solidvessel.account.payment.port.PaymentQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerQueryPort customerQueryPort;
    private final OrderQueryPort orderQueryPort;
    private final PaymentQueryPort paymentQueryPort;

    @GetMapping()
    public List<CustomerDataModel> getAll() {
        return customerQueryPort.getAll();
    }

    @GetMapping("/{id}")
    public CustomerDataModel getById(@PathVariable final Long id) {
        return customerQueryPort.getById(id);
    }

    @GetMapping("/{id}/detail")
    public CustomerDetailDataModel getDetailById(@PathVariable final Long id) {
        CustomerDataModel customer = customerQueryPort.getById(id);
        List<OrderDataModel> orders = orderQueryPort.getOrdersOfCustomer(id);
        List<PaymentDataModel> payments = paymentQueryPort.getPaymentsOfCustomer(id);
        return CustomerDetailDataModel.from(customer, orders, payments);
    }
}
