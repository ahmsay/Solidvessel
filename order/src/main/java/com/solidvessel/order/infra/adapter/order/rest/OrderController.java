package com.solidvessel.order.infra.adapter.order.rest;

import com.solidvessel.order.domain.customer.datamodel.CustomerDataModel;
import com.solidvessel.order.domain.customer.port.CustomerPort;
import com.solidvessel.order.domain.order.datamodel.OrderDataModel;
import com.solidvessel.order.domain.order.datamodel.OrderDetailDataModel;
import com.solidvessel.order.domain.order.port.OrderPort;
import com.solidvessel.order.domain.payment.datamodel.PaymentDataModel;
import com.solidvessel.order.domain.payment.port.PaymentPort;
import com.solidvessel.shared.infra.rest.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderPort orderPort;
    private final CustomerPort customerPort;
    private final PaymentPort paymentPort;

    public OrderController(OrderPort orderPort, CustomerPort customerPort, PaymentPort paymentPort) {
        this.orderPort = orderPort;
        this.customerPort = customerPort;
        this.paymentPort = paymentPort;
    }

    @GetMapping()
    public Response<List<OrderDataModel>> getAll() {
        return new Response<>(orderPort.getAll());
    }

    @GetMapping("/{id}")
    public Response<OrderDataModel> getById(@PathVariable final Long id) {
        return new Response<>(orderPort.getById(id));
    }

    @GetMapping("/{id}/detail")
    public Response<OrderDetailDataModel> getDetailById(@PathVariable final Long id) {
        OrderDataModel order = orderPort.getById(id);
        CustomerDataModel customer = customerPort.getCustomerOfOrder(order.customerId());
        PaymentDataModel payment = paymentPort.getPaymentOfOrder(order.paymentId());
        return new Response<>(OrderDetailDataModel.from(order, customer, payment));
    }

    @GetMapping("/ofCustomer/{customerId}")
    public Response<List<OrderDataModel>> getByCustomerId(@PathVariable final Long customerId) {
        return new Response<>(orderPort.getByCustomerId(customerId));
    }
}
