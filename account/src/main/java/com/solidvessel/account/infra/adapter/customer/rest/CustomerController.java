package com.solidvessel.account.infra.adapter.customer.rest;

import com.solidvessel.account.domain.customer.port.CustomerPort;
import com.solidvessel.account.domain.order.port.OrderPort;
import com.solidvessel.account.domain.payment.port.PaymentPort;
import com.solidvessel.account.infra.adapter.customer.rest.response.CustomerDetailResponse;
import com.solidvessel.account.infra.adapter.customer.rest.response.CustomerResponse;
import com.solidvessel.account.infra.adapter.order.rest.response.OrdersResponse;
import com.solidvessel.account.infra.adapter.payment.rest.response.PaymentsResponse;
import com.solidvessel.shared.auth.SessionUtil;
import jakarta.servlet.http.HttpServletRequest;
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
    public List<CustomerResponse> getAll() {
        return customerPort.getAll();
    }

    @GetMapping("/{id}")
    public CustomerResponse getById(@PathVariable final Long id) {
        return customerPort.getById(id);
    }

    @GetMapping("/{id}/detail")
    public CustomerDetailResponse getDetailById(@PathVariable final Long id, final HttpServletRequest request) {
        String session = SessionUtil.getSession(request);
        CustomerResponse customer = customerPort.getById(id);
        OrdersResponse orders = orderPort.getOrdersOfCustomer(id, session);
        PaymentsResponse payments = paymentPort.getPaymentsOfCustomer(id, session);
        return CustomerDetailResponse.from(customer, orders, payments);
    }
}
