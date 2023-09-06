package com.solidvessel.account.infra.adapter.in.customer.rest;

import com.solidvessel.account.domain.customer.datamodel.CustomerDataModel;
import com.solidvessel.account.domain.customer.datamodel.CustomerDetailDataModel;
import com.solidvessel.account.domain.customer.port.CustomerQueryPort;
import com.solidvessel.account.domain.order.datamodel.OrderDataModel;
import com.solidvessel.account.domain.order.port.OrderQueryPort;
import com.solidvessel.account.domain.payment.datamodel.PaymentDataModel;
import com.solidvessel.account.domain.payment.port.PaymentQueryPort;
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
