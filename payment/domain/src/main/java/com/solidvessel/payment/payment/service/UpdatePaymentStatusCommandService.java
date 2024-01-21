package com.solidvessel.payment.payment.service;

import com.solidvessel.payment.payment.event.PaymentApprovedEvent;
import com.solidvessel.payment.payment.model.Payment;
import com.solidvessel.payment.payment.port.PaymentPort;
import com.solidvessel.payment.payment.port.PaymentQueryPort;
import com.solidvessel.payment.product.event.ProductsCheckedEvent;
import com.solidvessel.shared.event.EventPublisher;
import com.solidvessel.shared.service.CommandService;
import com.solidvessel.shared.service.DomainComponent;
import com.solidvessel.shared.service.OperationResult;
import com.solidvessel.shared.service.ResultType;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class UpdatePaymentStatusCommandService implements CommandService<ProductsCheckedEvent> {

    private final PaymentQueryPort paymentQueryPort;
    private final PaymentPort paymentPort;
    private final EventPublisher<PaymentApprovedEvent> paymentApprovedEventPublisher;

    @Override
    public OperationResult execute(ProductsCheckedEvent command) {
        Payment payment = paymentQueryPort.getById(command.paymentId());
        if (command.areProductsAvailable()) {
            payment.approve();
            paymentPort.save(payment);
            paymentApprovedEventPublisher.publish(new PaymentApprovedEvent(command.paymentId(), command.customerId()));
        } else {
            payment.cancel();
            paymentPort.save(payment);
        }
        return new OperationResult("Payment status is updated.", ResultType.SUCCESS);
    }
}
