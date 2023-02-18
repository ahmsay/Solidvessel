package com.solidvessel.account.infra.adapter.customer.rest;

import com.solidvessel.account.domain.customer.datamodel.CustomerDataModel;
import com.solidvessel.account.domain.customer.datamodel.CustomerDetailDataModel;
import com.solidvessel.account.domain.customer.port.CustomerPort;
import com.solidvessel.account.domain.order.datamodel.OrderDataModel;
import com.solidvessel.account.domain.order.port.OrderPort;
import com.solidvessel.account.domain.payment.datamodel.PaymentDataModel;
import com.solidvessel.account.domain.payment.port.PaymentPort;
import com.solidvessel.shared.infra.rest.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerPort customerPort;
    private final OrderPort orderPort;
    private final PaymentPort paymentPort;

    public CustomerController(CustomerPort customerPort, OrderPort orderPort, PaymentPort paymentPort) {
        this.customerPort = customerPort;
        this.orderPort = orderPort;
        this.paymentPort = paymentPort;
    }

    @GetMapping()
    public Response<List<CustomerDataModel>> getAll() {
        return new Response<>(customerPort.getAll());
    }

    @GetMapping("/{id}")
    public Response<CustomerDataModel> getById(@PathVariable final Long id) {
        return new Response<>(customerPort.getById(id));
    }

    @GetMapping("/{id}/detail")
    public Response<CustomerDetailDataModel> getDetailById(@PathVariable final Long id) {
        CustomerDataModel customer = customerPort.getById(id);
        List<OrderDataModel> orders = orderPort.getOrdersOfCustomer(id);
        List<PaymentDataModel> payments = paymentPort.getPaymentsOfCustomer(id);
        return new Response<>(CustomerDetailDataModel.from(customer, orders, payments));
    }
}
