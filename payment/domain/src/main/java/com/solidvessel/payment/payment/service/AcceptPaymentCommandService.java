package com.solidvessel.payment.payment.service;

import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.cart.port.CartPort;
import com.solidvessel.payment.cart.port.CartQueryPort;
import com.solidvessel.payment.common.exception.PaymentDomainException;
import com.solidvessel.payment.payment.event.PaymentSavedEvent;
import com.solidvessel.payment.payment.model.Payment;
import com.solidvessel.payment.payment.port.PaymentPort;
import com.solidvessel.payment.payment.service.command.AcceptPaymentCommand;
import com.solidvessel.shared.event.EventPublisher;
import com.solidvessel.shared.service.CommandService;
import com.solidvessel.shared.service.DomainComponent;
import com.solidvessel.shared.service.OperationResult;
import com.solidvessel.shared.service.ResultType;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class AcceptPaymentCommandService implements CommandService<AcceptPaymentCommand, OperationResult> {

    private final CartPort cartPort;
    private final CartQueryPort cartQueryPort;
    private final PaymentPort paymentPort;
    private final EventPublisher<PaymentSavedEvent> paymentSavedEventPublisher;

    @Override
    public OperationResult execute(AcceptPaymentCommand command) {
        String customerId = command.customerId();
        Cart cart = cartQueryPort.getByCustomerId(customerId);
        checkIfTheCartIsEmpty(cart);
        savePayment(customerId, cart);
        saveCart(cart);
        return new OperationResult("Payment is accepted.", ResultType.SUCCESS);
    }

    private void checkIfTheCartIsEmpty(Cart cart) {
        if (cart.isEmpty()) {
            throw new PaymentDomainException("Your cart is empty.");
        }
    }

    private void savePayment(String customerId, Cart cart) {
        Long paymentId = paymentPort.create(Payment.newPayment(customerId, cart));
        paymentSavedEventPublisher.publish(new PaymentSavedEvent(paymentId, customerId, cart.getProductQuantities()));
    }

    private void saveCart(Cart cart) {
        cart.empty();
        cartPort.save(cart);
    }
}
