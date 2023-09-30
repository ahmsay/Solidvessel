package com.solidvessel.payment.adapter.in.payment.rest;

import com.solidvessel.payment.adapter.in.payment.rest.request.AcceptPaymentRequest;
import com.solidvessel.payment.customer.datamodel.CustomerDataModel;
import com.solidvessel.payment.customer.port.CustomerQueryPort;
import com.solidvessel.payment.payment.datamodel.PaymentDataModel;
import com.solidvessel.payment.payment.datamodel.PaymentDetailDataModel;
import com.solidvessel.payment.payment.port.PaymentQueryPort;
import com.solidvessel.payment.payment.service.AcceptPaymentCommand;
import com.solidvessel.shared.service.CommandService;
import com.solidvessel.shared.service.OperationResult;
import lombok.RequiredArgsConstructor;
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

    @GetMapping()
    public List<PaymentDataModel> getAll() {
        return paymentQueryPort.getAll();
    }

    @GetMapping("/{id}")
    public PaymentDataModel getById(@PathVariable final Long id) {
        return paymentQueryPort.getById(id);
    }

    @GetMapping("/{id}/detail")
    public PaymentDetailDataModel getDetailById(@PathVariable final Long id) {
        PaymentDataModel payment = paymentQueryPort.getById(id);
        CustomerDataModel customer = customerQueryPort.getCustomerOfPayment(payment.customerId());
        return PaymentDetailDataModel.from(payment, customer);
    }

    @GetMapping("/ofCustomer/{customerId}")
    public List<PaymentDataModel> getByCustomerId(@PathVariable final Long customerId) {
        return paymentQueryPort.getByCustomerId(customerId);
    }

    @PostMapping
    public OperationResult acceptPayment(final AcceptPaymentRequest request) {
        return acceptPaymentCommandService.execute(request.toCommand());
    }
}
