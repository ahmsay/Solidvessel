package com.solidvessel.payment.domain.payment.service;

import com.solidvessel.payment.domain.payment.model.Payment;
import com.solidvessel.payment.domain.payment.port.CartPort;
import com.solidvessel.payment.domain.payment.service.command.AddToCartCommand;
import com.solidvessel.payment.domain.product.model.Product;
import com.solidvessel.payment.domain.product.port.ProductPort;
import com.solidvessel.shared.domain.service.CommandService;
import com.solidvessel.shared.domain.service.OperationResult;
import com.solidvessel.shared.domain.service.ResultType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddToCartCommandService implements CommandService<AddToCartCommand> {

    private final CartPort cartPort;
    private final ProductPort productPort;

    public OperationResult execute(AddToCartCommand command) {
        Optional<Product> productOptional = productPort.getById(command.productId());
        if (productOptional.isEmpty()) {
            return new OperationResult("The product is not available in stocks.", ResultType.ERROR);
        }
        Product product = productOptional.get();
        Long customerId = command.customerId();
        Optional<Payment> cartOptional = cartPort.getByCustomerId(customerId);
        Payment cart;
        if (cartOptional.isPresent()) {
            cart = cartOptional.get();
            cart.addProduct(product);
        } else {
            cart = Payment.newPayment(product, customerId);
        }
        cartPort.save(cart);
        return new OperationResult("Product is added to the cart.", ResultType.SUCCESS);
    }
}
