package com.solidvessel.payment.cart.service;

import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.cart.port.CartPort;
import com.solidvessel.payment.cart.port.CartQueryPort;
import com.solidvessel.payment.cart.service.command.RemoveFromCartCommand;
import com.solidvessel.payment.common.exception.PaymentDomainException;
import com.solidvessel.shared.service.ResultType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RemoveFromCartCommandServiceTest {

    @Mock
    private CartPort cartPort;

    @Mock
    private CartQueryPort cartQueryPort;

    @Test
    void removeFromCart() {
        var command = new RemoveFromCartCommand("123", 3L);
        var commandService = new RemoveFromCartCommandService(cartPort, cartQueryPort);
        Map<Long, Integer> productQuantities = new HashMap<>() {{
            put(3L, 7);
        }};
        Cart cart = new Cart(1L, "123", productQuantities);
        when(cartQueryPort.getByCustomerId("123")).thenReturn(cart);
        var operationResult = commandService.execute(command);
        verify(cartPort).save(any(Cart.class));
        assertEquals(ResultType.SUCCESS, operationResult.resultType());
        assertEquals(6, cart.getProductQuantities().get(3L));
    }

    @Test
    void productIsNotAvailable() {
        var command = new RemoveFromCartCommand("123", 3L);
        var commandService = new RemoveFromCartCommandService(cartPort, cartQueryPort);
        when(cartQueryPort.getByCustomerId("123")).thenReturn(Cart.newCart("123"));
        assertThrows(PaymentDomainException.class, () -> commandService.execute(command));
        verifyNoInteractions(cartPort);
    }
}
