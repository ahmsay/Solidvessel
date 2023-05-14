package com.solidvessel.payment.domain.cart.service;

import com.solidvessel.payment.domain.cart.model.Cart;
import com.solidvessel.payment.domain.cart.port.CartPort;
import com.solidvessel.payment.domain.cart.service.command.AddToCartCommand;
import com.solidvessel.payment.domain.product.port.ProductPort;
import com.solidvessel.shared.domain.service.CommandService;
import com.solidvessel.shared.domain.service.OperationResult;
import com.solidvessel.shared.domain.service.ResultType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddToCartCommandService implements CommandService<AddToCartCommand> {

    private final CartPort cartPort;
    private final ProductPort productPort;

    public OperationResult execute(AddToCartCommand command) {
        Long productId = command.productId();
        boolean available = productPort.isAvailable(productId, command.quantity());
        if (!available) {
            return new OperationResult("The product is not available in stocks.", ResultType.ERROR);
        }
        Cart cart = cartPort.getByCustomerId(command.customerId());
        cart.addProduct(productId);
        cartPort.save(cart);
        return new OperationResult("Product is added to the cart.", ResultType.SUCCESS);
    }
}
