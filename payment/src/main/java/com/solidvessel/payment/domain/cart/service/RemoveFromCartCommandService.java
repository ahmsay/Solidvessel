package com.solidvessel.payment.domain.cart.service;

import com.solidvessel.payment.domain.cart.model.Cart;
import com.solidvessel.payment.domain.cart.port.CartPort;
import com.solidvessel.payment.domain.cart.service.command.RemoveFromCartCommand;
import com.solidvessel.payment.domain.common.exception.PaymentDomainException;
import com.solidvessel.shared.domain.service.CommandService;
import com.solidvessel.shared.domain.service.OperationResult;
import com.solidvessel.shared.domain.service.ResultType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemoveFromCartCommandService implements CommandService<RemoveFromCartCommand> {

    private final CartPort cartPort;

    @Override
    public OperationResult execute(RemoveFromCartCommand command) {
        Cart cart = cartPort.getByCustomerId(command.customerId());
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
