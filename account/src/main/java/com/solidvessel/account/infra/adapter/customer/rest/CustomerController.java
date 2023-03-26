package com.solidvessel.account.infra.adapter.customer.rest;

import com.solidvessel.account.domain.customer.datamodel.CustomerDataModel;
import com.solidvessel.account.domain.customer.datamodel.CustomerDetailDataModel;
import com.solidvessel.account.domain.customer.port.CustomerPort;
import com.solidvessel.account.domain.order.datamodel.OrderDataModel;
import com.solidvessel.account.domain.order.port.OrderPort;
import com.solidvessel.account.domain.payment.datamodel.PaymentDataModel;
import com.solidvessel.account.domain.payment.port.PaymentPort;
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

    private final CustomerPort customerPort;
    private final OrderPort orderPort;
    private final PaymentPort paymentPort;

    @GetMapping()
    public List<CustomerDataModel> getAll() {
        return customerPort.getAll();
    }

    @GetMapping("/{id}")
    public CustomerDataModel getById(@PathVariable final Long id) {
        return customerPort.getById(id);
    }

    @GetMapping("/{id}/detail")
    public CustomerDetailDataModel getDetailById(@PathVariable final Long id) {
        CustomerDataModel customer = customerPort.getById(id);
        List<OrderDataModel> orders = orderPort.getOrdersOfCustomer(id);
        List<PaymentDataModel> payments = paymentPort.getPaymentsOfCustomer(id);
        return CustomerDetailDataModel.from(customer, orders, payments);
    }
}
