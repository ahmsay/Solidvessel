package com.solidvessel.payment.cart.service;

import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.cart.port.CartPort;
import com.solidvessel.payment.cart.port.CartQueryPort;
import com.solidvessel.payment.product.event.ProductAvailableEvent;
import com.solidvessel.payment.product.model.ProductCategory;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddToCartCommandServiceTest extends BaseUnitTest {

    @Mock
    private CartPort cartPort;

    @Mock
    private CartQueryPort cartQueryPort;

    @Test
    void addToCart() {
        var event = new ProductAvailableEvent(1L, "cellphone", 530D, ProductCategory.ELECTRONICS, 3, "123");
        var commandService = new AddToCartCommandService(cartPort, cartQueryPort);
        Cart cart = Cart.newCart("123");
        when(cartQueryPort.getByCustomerId(event.customerId())).thenReturn(cart);
        commandService.execute(event);
        verify(cartPort).save(cart);
    }
}
