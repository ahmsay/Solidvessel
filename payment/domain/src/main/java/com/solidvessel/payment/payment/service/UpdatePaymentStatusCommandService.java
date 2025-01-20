package com.solidvessel.payment.payment.service;

import com.solidvessel.payment.common.exception.PaymentDomainException;
import com.solidvessel.payment.customer.model.Customer;
import com.solidvessel.payment.customer.port.CustomerQueryPort;
import com.solidvessel.payment.payment.event.PaymentApprovedEvent;
import com.solidvessel.payment.payment.model.Payment;
import com.solidvessel.payment.payment.port.PaymentPort;
import com.solidvessel.payment.payment.port.PaymentQueryPort;
import com.solidvessel.payment.product.event.ProductsCheckedEvent;
import com.solidvessel.shared.event.EventPublisher;
import com.solidvessel.shared.service.DomainComponent;
import com.solidvessel.shared.service.VoidCommandService;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class UpdatePaymentStatusCommandService implements VoidCommandService<ProductsCheckedEvent> {

    private final PaymentQueryPort paymentQueryPort;
    private final PaymentPort paymentPort;
    private final EventPublisher<PaymentApprovedEvent> paymentApprovedEventPublisher;
    private final CustomerQueryPort customerQueryPort;

    @Override
    public void execute(ProductsCheckedEvent command) {
        Payment payment = paymentQueryPort.getById(command.paymentId());
        if (command.areProductsAvailable()) {
            Customer customer = customerQueryPort.getById(command.customerId()).orElseThrow(() -> new PaymentDomainException("Customer not found!"));
            payment.approve();
            paymentPort.update(payment);
            paymentApprovedEventPublisher.publish(new PaymentApprovedEvent(command.paymentId(), command.customerId(), customer.getAddress()));
        } else {
            payment.cancel();
            paymentPort.update(payment);
        }
    }
}
