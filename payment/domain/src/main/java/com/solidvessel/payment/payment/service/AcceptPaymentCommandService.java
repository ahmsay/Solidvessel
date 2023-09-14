package com.solidvessel.payment.payment.service;

import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.cart.port.CartPort;
import com.solidvessel.payment.cart.port.CartQueryPort;
import com.solidvessel.payment.common.exception.PaymentDomainException;
import com.solidvessel.payment.payment.event.PaymentSavedEvent;
import com.solidvessel.payment.payment.model.Payment;
import com.solidvessel.payment.payment.port.PaymentPort;
import com.solidvessel.payment.product.datamodel.ProductDataModel;
import com.solidvessel.payment.product.port.ProductQueryPort;
import com.solidvessel.payment.product.service.ProductQuantityDomainService;
import com.solidvessel.shared.domain.event.EventPublisher;
import com.solidvessel.shared.domain.service.CommandService;
import com.solidvessel.shared.domain.service.OperationResult;
import com.solidvessel.shared.domain.service.ResultType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AcceptPaymentCommandService implements CommandService<AcceptPaymentCommand> {

    private final CartPort cartPort;
    private final CartQueryPort cartQueryPort;
    private final ProductQueryPort productQueryPort;
    private final PaymentPort paymentPort;
    private final ProductQuantityDomainService productQuantityDomainService;
    private final EventPublisher<PaymentSavedEvent> paymentSavedEventPublisher;

    @Override
    public OperationResult execute(AcceptPaymentCommand command) {
        Long customerId = command.customerId();
        Cart cart = cartQueryPort.getByCustomerId(customerId);
        checkIfTheCartIsEmpty(cart);
        List<ProductDataModel> productsFromInventory = productQueryPort.getProductsOfCart(cart.getProductIds());
        checkIfProductsAreAvailable(cart, productsFromInventory);
        Long paymentId = savePayment(customerId, productsFromInventory, cart);
        var productQuantities = cart.getProductQuantities();
        saveCart(cart);
        paymentSavedEventPublisher.publish(new PaymentSavedEvent(paymentId, customerId, productQuantities));
        return new OperationResult("Payment is accepted.", ResultType.SUCCESS);
    }

    private void checkIfTheCartIsEmpty(Cart cart) {
        if (cart.isEmpty()) {
            throw new PaymentDomainException("Your cart is empty.");
        }
    }

    private void checkIfProductsAreAvailable(Cart cart, List<ProductDataModel> productsFromInventory) {
        if (!productQuantityDomainService.areQuantitiesAvailable(cart.getProductQuantities(), productsFromInventory)) {
            throw new PaymentDomainException("Selected products are not available with specified quantity.");
        }
    }

    private Long savePayment(Long customerId, List<ProductDataModel> productsFromInventory, Cart cart) {
        Payment payment = Payment.newPayment(customerId, productsFromInventory, cart.getProductQuantities());
        return paymentPort.save(payment);
    }

    private void saveCart(Cart cart) {
        cart.empty();
        cartPort.save(cart);
    }
}
