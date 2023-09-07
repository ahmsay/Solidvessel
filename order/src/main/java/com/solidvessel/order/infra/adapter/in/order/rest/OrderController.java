package com.solidvessel.order.infra.adapter.in.order.rest;

import com.solidvessel.order.domain.customer.datamodel.CustomerDataModel;
import com.solidvessel.order.domain.customer.port.CustomerQueryPort;
import com.solidvessel.order.domain.order.datamodel.OrderDataModel;
import com.solidvessel.order.domain.order.datamodel.OrderDetailDataModel;
import com.solidvessel.order.domain.order.port.OrderQueryPort;
import com.solidvessel.order.domain.payment.datamodel.PaymentDataModel;
import com.solidvessel.order.domain.payment.port.PaymentQueryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderQueryPort orderQueryPort;
    private final CustomerQueryPort customerQueryPort;
    private final PaymentQueryPort paymentQueryPort;

    @GetMapping()
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
    public List<OrderDataModel> getByCustomerId(@PathVariable final Long customerId) {
        return orderQueryPort.getByCustomerId(customerId);
    }
}
