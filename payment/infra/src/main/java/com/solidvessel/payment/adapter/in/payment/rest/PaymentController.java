package com.solidvessel.payment.adapter.in.payment.rest;

import com.solidvessel.payment.adapter.in.payment.rest.request.AcceptPaymentRequest;
import com.solidvessel.payment.customer.model.Customer;
import com.solidvessel.payment.customer.port.CustomerQueryPort;
import com.solidvessel.payment.payment.datamodel.PaymentDataModel;
import com.solidvessel.payment.payment.datamodel.PaymentDetailDataModel;
import com.solidvessel.payment.payment.model.Payment;
import com.solidvessel.payment.payment.port.PaymentQueryPort;
import com.solidvessel.payment.payment.service.AcceptPaymentCommand;
import com.solidvessel.shared.service.CommandService;
import com.solidvessel.shared.service.OperationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentQueryPort paymentQueryPort;
    private final CustomerQueryPort customerQueryPort;
    private final CommandService<AcceptPaymentCommand> acceptPaymentCommandService;

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/")
    public List<PaymentDataModel> getAll() {
        return paymentQueryPort.getAll().stream().map(PaymentDataModel::from).toList();
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/{id}")
    public PaymentDataModel getById(@PathVariable final Long id) {
        return PaymentDataModel.from(paymentQueryPort.getById(id));
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/{id}/detail")
    public PaymentDetailDataModel getDetailById(@PathVariable final Long id) {
        Payment payment = paymentQueryPort.getById(id);
        Customer customer = customerQueryPort.getCustomerOfPayment(payment.getCustomerId());
        return PaymentDetailDataModel.from(payment, customer);
    }

    @PreAuthorize("hasAuthority('MANAGER')")
    @GetMapping("/ofCustomer/{customerId}")
    public List<PaymentDataModel> getByCustomerId(@PathVariable final String customerId) {
        return paymentQueryPort.getByCustomerId(customerId).stream().map(PaymentDataModel::from).toList();
    }

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @PostMapping("/accept")
    public OperationResult acceptPayment(final AcceptPaymentRequest request) {
        return acceptPaymentCommandService.execute(request.toCommand());
    }
}
