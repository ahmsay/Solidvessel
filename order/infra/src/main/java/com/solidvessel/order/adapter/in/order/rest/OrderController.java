package com.solidvessel.order.adapter.in.order.rest;

import com.solidvessel.order.customer.datamodel.CustomerDataModel;
import com.solidvessel.order.customer.port.CustomerQueryPort;
import com.solidvessel.order.order.datamodel.OrderDataModel;
import com.solidvessel.order.order.datamodel.OrderDetailDataModel;
import com.solidvessel.order.order.port.OrderQueryPort;
import com.solidvessel.order.payment.datamodel.PaymentDataModel;
import com.solidvessel.order.payment.port.PaymentQueryPort;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/")
    public List<OrderDataModel> getAll() {
        return orderQueryPort.getAll();
    }

    @GetMapping("/{id}")
    public OrderDataModel getById(@PathVariable final Long id) {
        return orderQueryPort.getById(id);
    }

    @GetMapping("/{id}/detail")
    public OrderDetailDataModel getDetailById(@PathVariable final Long id) {
        OrderDataModel order = orderQueryPort.getById(id);
        CustomerDataModel customer = customerQueryPort.getCustomerOfOrder(order.customerId());
        PaymentDataModel payment = paymentQueryPort.getPaymentOfOrder(order.paymentId());
        return OrderDetailDataModel.from(order, customer, payment);
    }

    @GetMapping("/ofCustomer/{customerId}")
    public List<OrderDataModel> getByCustomerId(@PathVariable final String customerId) {
        return orderQueryPort.getByCustomerId(customerId);
    }
}
