package com.solidvessel.payment.payment.service;

import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.cart.port.CartPort;
import com.solidvessel.payment.cart.port.CartQueryPort;
import com.solidvessel.payment.common.exception.PaymentDomainException;
import com.solidvessel.payment.payment.event.PaymentSavedEvent;
import com.solidvessel.payment.payment.model.Payment;
import com.solidvessel.payment.payment.model.PaymentStatus;
import com.solidvessel.payment.payment.port.PaymentPort;
import com.solidvessel.payment.payment.service.command.AcceptPaymentCommand;
import com.solidvessel.payment.product.model.Product;
import com.solidvessel.payment.product.model.ProductCategory;
import com.solidvessel.shared.event.EventPublisher;
import com.solidvessel.shared.service.OperationResult;
import com.solidvessel.shared.service.ResultType;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        Product product1 = new Product(4L, "chair", 15D, ProductCategory.FURNITURE, 1);
        Product product2 = new Product(5L, "apple", 3D, ProductCategory.ELECTRONICS, 3);
        Map<Long, Product> productMap = new HashMap<>() {{
            put(4L, product1);
            put(5L, product2);
        }};
        Cart cart = new Cart("123", productMap);
        Payment payment = new Payment("123", List.of(product1, product2), 24D, PaymentStatus.PENDING);
        when(cartQueryPort.getByCustomerId("123")).thenReturn(cart);
        when(paymentPort.create(payment)).thenReturn(1L);

        OperationResult operationResult = commandService.execute(command);
        verify(cartPort).save(cart);
        verify(paymentPort).create(payment);
        verify(paymentSavedEventPublisher).publish(new PaymentSavedEvent(1L, "123", Map.of(4L, 1, 5L, 3)));
        assertEquals(ResultType.SUCCESS, operationResult.resultType());
    }

    @Test
    void cartIsEmpty() {
        when(cartQueryPort.getByCustomerId("123")).thenReturn(Cart.newCart("123"));
        assertThrows(PaymentDomainException.class, () -> commandService.execute(command));
        verifyNoInteractions(cartPort, paymentPort, paymentSavedEventPublisher);
    }
}
