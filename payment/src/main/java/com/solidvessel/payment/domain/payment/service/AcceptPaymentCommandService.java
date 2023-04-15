package com.solidvessel.payment.domain.payment.service;

import com.solidvessel.payment.domain.cart.model.Cart;
import com.solidvessel.payment.domain.cart.port.CartPort;
import com.solidvessel.payment.domain.payment.event.PaymentSavedEvent;
import com.solidvessel.payment.domain.payment.model.Payment;
import com.solidvessel.payment.domain.payment.port.PaymentPort;
import com.solidvessel.payment.domain.product.datamodel.ProductDataModel;
import com.solidvessel.payment.domain.product.port.ProductRestPort;
import com.solidvessel.shared.domain.event.EventPublisher;
import com.solidvessel.shared.domain.service.CommandService;
import com.solidvessel.shared.domain.service.OperationResult;
import com.solidvessel.shared.domain.service.ResultType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AcceptPaymentCommandService implements CommandService<AcceptPaymentCommand> {

    private final CartPort cartPort;
    private final ProductRestPort productPort;
    private final PaymentPort paymentPort;
    private final EventPublisher<PaymentSavedEvent> paymentSavedEventPublisher;

    @Override
    public OperationResult execute(AcceptPaymentCommand command) {
        Long customerId = command.customerId();
        Cart cart = cartPort.getByCustomerId(customerId);
        if (cart.isEmpty()) {
            return new OperationResult("Your cart is empty.", ResultType.ERROR);
        }
        Set<Long> productIds = cart.getProducts().keySet();
        List<ProductDataModel> products = productPort.getProductsOfCart(productIds);
        Payment payment = Payment.newPayment(customerId, products);
        Long paymentId = paymentPort.save(payment);
        cart.empty();
        cartPort.save(cart);
        paymentSavedEventPublisher.publish(new PaymentSavedEvent(paymentId, customerId));
        return new OperationResult("Payment is accepted.", ResultType.SUCCESS);
    }
}
