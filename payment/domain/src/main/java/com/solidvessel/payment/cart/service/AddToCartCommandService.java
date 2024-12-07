package com.solidvessel.payment.cart.service;

import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.cart.port.CartPort;
import com.solidvessel.payment.cart.port.CartQueryPort;
import com.solidvessel.payment.product.event.ProductAvailableEvent;
import com.solidvessel.shared.service.DomainComponent;
import com.solidvessel.shared.service.VoidCommandService;
import lombok.RequiredArgsConstructor;

@DomainComponent
@RequiredArgsConstructor
public class AddToCartCommandService implements VoidCommandService<ProductAvailableEvent> {

    private final CartPort cartPort;
    private final CartQueryPort cartQueryPort;

    public void execute(ProductAvailableEvent event) {
        Cart cart = cartQueryPort.getByCustomerId(event.customerId());
        cart.addProduct(event.toDomainModel());
        cartPort.save(cart);
    }
}
