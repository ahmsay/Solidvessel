package com.solidvessel.payment.cart.service;

import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.cart.port.CartPort;
import com.solidvessel.payment.cart.port.CartQueryPort;
import com.solidvessel.payment.cart.service.command.ClearCartCommand;
import com.solidvessel.payment.common.exception.PaymentDomainException;
import com.solidvessel.shared.service.CommandService;
import com.solidvessel.shared.service.DomainComponent;
import com.solidvessel.shared.service.OperationResult;
import com.solidvessel.shared.service.ResultType;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class ClearCartCommandService implements CommandService<ClearCartCommand, OperationResult> {

    private final CartPort cartPort;
    private final CartQueryPort cartQueryPort;

    @Override
    public OperationResult execute(ClearCartCommand command) {
        Cart cart = cartQueryPort.getByCustomerId(command.customerId());
        if (cart.isEmpty()) {
            throw new PaymentDomainException("Your cart is already empty.");
        }
        cart.empty();
        cartPort.save(cart);
        return new OperationResult("Your cart is now empty.", ResultType.SUCCESS);
    }
}
