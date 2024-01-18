package com.solidvessel.payment.payment.service;

import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.cart.port.CartPort;
import com.solidvessel.payment.cart.port.CartQueryPort;
import com.solidvessel.payment.common.exception.PaymentDomainException;
import com.solidvessel.payment.payment.event.PaymentSavedEvent;
import com.solidvessel.payment.payment.model.Payment;
import com.solidvessel.payment.payment.port.PaymentPort;
import com.solidvessel.payment.product.model.Product;
import com.solidvessel.payment.product.model.ProductCategory;
import com.solidvessel.shared.event.EventPublisher;
import com.solidvessel.shared.service.ResultType;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AcceptPaymentCommandServiceTest extends BaseUnitTest {

    @Mock
    private CartPort cartPort;

    @Mock
    private CartQueryPort cartQueryPort;

    @Mock
    private PaymentPort paymentPort;

    @Mock
    private EventPublisher<PaymentSavedEvent> paymentSavedEventPublisher;

    private AcceptPaymentCommand command;

    private AcceptPaymentCommandService commandService;

    @BeforeEach
    void init() {
        command = new AcceptPaymentCommand("123");
        commandService = new AcceptPaymentCommandService(cartPort, cartQueryPort, paymentPort, paymentSavedEventPublisher);
    }

    @Test
    void acceptPayment() {
        Map<Long, Product> products = new HashMap<>() {{
            put(1L, new Product(4L, "chair", 15D, ProductCategory.FURNITURE, 1));
            put(4L, new Product(5L, "apple", 3D, ProductCategory.ELECTRONICS, 3));
        }};
        Cart cart = new Cart(1L, "123", products);
        when(cartQueryPort.getByCustomerId("123")).thenReturn(cart);

        var operationResult = commandService.execute(command);
        verify(cartPort).save(any(Cart.class));
        verify(paymentPort).save(any(Payment.class));
        verify(paymentSavedEventPublisher).publish(any(PaymentSavedEvent.class));
        assertEquals(ResultType.SUCCESS, operationResult.resultType());
    }

    @Test
    void cartIsEmpty() {
        when(cartQueryPort.getByCustomerId("123")).thenReturn(Cart.newCart("123"));
        assertThrows(PaymentDomainException.class, () -> commandService.execute(command));
        verifyNoInteractions(cartPort, paymentPort, paymentSavedEventPublisher);
    }
}
