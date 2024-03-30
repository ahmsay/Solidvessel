package com.solidvessel.payment.cart.service;

import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.cart.port.CartPort;
import com.solidvessel.payment.cart.port.CartQueryPort;
import com.solidvessel.payment.product.event.ProductAvailableEvent;
import com.solidvessel.shared.service.CommandService;
import com.solidvessel.shared.service.DomainComponent;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class AddToCartCommandService implements CommandService<ProductAvailableEvent, Void> {

    private final CartPort cartPort;
    private final CartQueryPort cartQueryPort;

    public Void execute(ProductAvailableEvent event) {
        Cart cart = cartQueryPort.getByCustomerId(event.customerId());
        cart.addProduct(event.toDomainModel());
        cartPort.save(cart);
        return null;
    }
}
