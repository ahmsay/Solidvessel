package com.solidvessel.payment.cart.service;

import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.cart.port.CartPort;
import com.solidvessel.payment.cart.port.CartQueryPort;
import com.solidvessel.payment.cart.service.command.RemoveFromCartCommand;
import com.solidvessel.payment.common.exception.PaymentDomainException;
import com.solidvessel.shared.service.CommandService;
import com.solidvessel.shared.service.DomainComponent;
import com.solidvessel.shared.service.OperationResult;
import com.solidvessel.shared.service.ResultType;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class RemoveFromCartCommandService implements CommandService<RemoveFromCartCommand, OperationResult> {

    private final CartPort cartPort;
    private final CartQueryPort cartQueryPort;

    @Override
    public OperationResult execute(RemoveFromCartCommand command) {
        Cart cart = cartQueryPort.getByCustomerId(command.customerId());
        Long productId = command.productId();
        checkIfProductIsInCart(cart, productId);
        cart.removeProduct(productId);
        cartPort.save(cart);
        return new OperationResult("Product is removed from the cart.", ResultType.SUCCESS);
    }

    private void checkIfProductIsInCart(Cart cart, Long productId) {
        if (!cart.doesProductExist(productId)) {
            throw new PaymentDomainException("Product is not available in the cart.");
        }
    }
}
