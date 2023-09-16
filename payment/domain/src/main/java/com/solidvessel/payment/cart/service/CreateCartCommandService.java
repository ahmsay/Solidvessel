package com.solidvessel.payment.cart.service;

import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.cart.port.CartPort;
import com.solidvessel.payment.cart.service.command.CreateCartCommand;
import com.solidvessel.shared.domain.service.CommandService;
import com.solidvessel.shared.domain.service.DomainComponent;
import com.solidvessel.shared.domain.service.OperationResult;
import com.solidvessel.shared.domain.service.ResultType;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class CreateCartCommandService implements CommandService<CreateCartCommand> {

    private final CartPort cartPort;

    @Override
    public OperationResult execute(CreateCartCommand command) {
        Cart cart = Cart.newCart(command.customerId());
        cartPort.save(cart);
        return new OperationResult("Cart is created.", ResultType.SUCCESS);
    }
}
