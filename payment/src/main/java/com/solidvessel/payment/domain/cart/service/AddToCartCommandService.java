package com.solidvessel.payment.domain.cart.service;

import com.solidvessel.payment.domain.cart.model.Cart;
import com.solidvessel.payment.domain.cart.port.CartPort;
import com.solidvessel.payment.domain.cart.port.CartQueryPort;
import com.solidvessel.payment.domain.cart.service.command.AddToCartCommand;
import com.solidvessel.payment.domain.common.exception.PaymentDomainException;
import com.solidvessel.payment.domain.product.port.ProductQueryPort;
import com.solidvessel.shared.domain.service.CommandService;
import com.solidvessel.shared.domain.service.OperationResult;
import com.solidvessel.shared.domain.service.ResultType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddToCartCommandService implements CommandService<AddToCartCommand> {

    private final CartPort cartPort;
    private final CartQueryPort cartQueryPort;
    private final ProductQueryPort productQueryPort;

    public OperationResult execute(AddToCartCommand command) {
        checkProductAvailability(command);
        Cart cart = cartQueryPort.getByCustomerId(command.customerId());
        cart.addProduct(command.productId(), command.quantity());
        cartPort.save(cart);
        return new OperationResult("Product is added to the cart.", ResultType.SUCCESS);
    }

    private void checkProductAvailability(AddToCartCommand command) {
        boolean available = productQueryPort.isAvailable(command.productId(), command.quantity());
        if (!available) {
            throw new PaymentDomainException("The product is not available in stocks.");
        }
    }
}
