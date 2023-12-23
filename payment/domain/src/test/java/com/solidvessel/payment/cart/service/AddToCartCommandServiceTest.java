package com.solidvessel.payment.cart.service;

import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.cart.port.CartPort;
import com.solidvessel.payment.cart.port.CartQueryPort;
import com.solidvessel.payment.cart.service.command.AddToCartCommand;
import com.solidvessel.payment.common.exception.PaymentDomainException;
import com.solidvessel.payment.product.port.ProductQueryPort;
import com.solidvessel.shared.service.ResultType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddToCartCommandServiceTest {

    @Mock
    private CartPort cartPort;

    @Mock
    private CartQueryPort cartQueryPort;

    @Mock
    private ProductQueryPort productQueryPort;

    @Test
    void addToCart() {
        var command = new AddToCartCommand("123", 1L, 3);
        var commandService = new AddToCartCommandService(cartPort, cartQueryPort, productQueryPort);
        when(productQueryPort.isAvailable(1L, 3)).thenReturn(true);
        when(cartQueryPort.getByCustomerId("123")).thenReturn(Cart.newCart("123"));
        var operationResult = commandService.execute(command);
        verify(cartPort).save(any(Cart.class));
        assertEquals(ResultType.SUCCESS, operationResult.resultType());
    }

    @Test
    void productIsNotAvailable() {
        var command = new AddToCartCommand("123", 1L, 3);
        var commandService = new AddToCartCommandService(cartPort, cartQueryPort, productQueryPort);
        when(productQueryPort.isAvailable(1L, 3)).thenReturn(false);
        assertThrows(PaymentDomainException.class, () -> commandService.execute(command));
        verifyNoInteractions(cartPort);
    }
}
