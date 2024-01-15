package com.solidvessel.payment.cart.service;

import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.cart.port.CartPort;
import com.solidvessel.payment.cart.port.CartQueryPort;
import com.solidvessel.payment.cart.service.command.AddToCartCommand;
import com.solidvessel.shared.service.CommandService;
import com.solidvessel.shared.service.DomainComponent;
import com.solidvessel.shared.service.OperationResult;
import com.solidvessel.shared.service.ResultType;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class AddToCartCommandService implements CommandService<AddToCartCommand> {

    private final CartPort cartPort;
    private final CartQueryPort cartQueryPort;

    public OperationResult execute(AddToCartCommand command) {
        Cart cart = cartQueryPort.getByCustomerId(command.customerId());
        cart.addProduct(command.toDomainModel());
        cartPort.save(cart);
        return new OperationResult("Product is added to the cart.", ResultType.SUCCESS);
    }
}
