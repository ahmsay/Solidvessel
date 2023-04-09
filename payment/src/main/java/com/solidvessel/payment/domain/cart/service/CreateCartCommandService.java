package com.solidvessel.payment.domain.cart.service;

import com.solidvessel.payment.domain.cart.model.Cart;
import com.solidvessel.payment.domain.cart.port.CartPort;
import com.solidvessel.payment.domain.cart.service.command.CreateCartCommand;
import com.solidvessel.shared.domain.service.CommandService;
import com.solidvessel.shared.domain.service.OperationResult;
import com.solidvessel.shared.domain.service.ResultType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
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
