package com.solidvessel.payment.domain.payment.service;

import com.solidvessel.payment.domain.payment.port.CartPort;
import com.solidvessel.payment.domain.payment.service.command.AddToCartCommand;
import com.solidvessel.shared.domain.service.CommandService;
import com.solidvessel.shared.domain.service.OperationResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddToCartCommandService implements CommandService<AddToCartCommand> {

    private final CartPort cartPort;

    public OperationResult execute(AddToCartCommand command) {
        /*
        Product product = get the product
        if (product does not exist) {
            return error
        }
        Long customerId = command.customerId();
        if (cartPort.doesCartExist(customerId)) {
            Payment cart = cartPort.getByCustomerId(customerId);
            cart.addProduct(product);
            cartPort.update(cart);
        } else {
            Payment cart = Payment.newPayment(product);
            cartPort.add(cart);
        }*/
        return null;
    }
}
