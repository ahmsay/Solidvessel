package com.solidvessel.payment.payment.service;

import com.solidvessel.payment.cart.model.Cart;
import com.solidvessel.payment.cart.port.CartPort;
import com.solidvessel.payment.cart.port.CartQueryPort;
import com.solidvessel.payment.common.exception.PaymentDomainException;
import com.solidvessel.payment.payment.event.PaymentSavedEvent;
import com.solidvessel.payment.payment.model.Payment;
import com.solidvessel.payment.payment.port.PaymentPort;
import com.solidvessel.payment.product.datamodel.ProductDataModel;
import com.solidvessel.payment.product.port.ProductQueryPort;
import com.solidvessel.payment.product.service.ProductQuantityDomainService;
import com.solidvessel.shared.event.EventPublisher;
import com.solidvessel.shared.service.ResultType;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private ProductQueryPort productQueryPort;

    @Mock
    private PaymentPort paymentPort;

    @Mock
    private EventPublisher<PaymentSavedEvent> paymentSavedEventPublisher;

    private AcceptPaymentCommand command;

    private AcceptPaymentCommandService commandService;

    @BeforeEach
    void init() {
        command = new AcceptPaymentCommand("123");
        ProductQuantityDomainService productQuantityDomainService = new ProductQuantityDomainService();
        commandService = new AcceptPaymentCommandService(cartPort, cartQueryPort, productQueryPort, paymentPort, productQuantityDomainService, paymentSavedEventPublisher);
    }

    @Test
    void acceptPayment() {
        Map<Long, Integer> productQuantities = new HashMap<>() {{
            put(1L, 1);
            put(4L, 3);
        }};
        Cart cart = new Cart(1L, "123", productQuantities);
        when(cartQueryPort.getByCustomerId("123")).thenReturn(cart);

        List<ProductDataModel> productsFromInventory = new ArrayList<>() {{
            add(new ProductDataModel(1L, 5, "laptop", 234D));
            add(new ProductDataModel(4L, 9, "knife", 5D));
        }};
        when(productQueryPort.getProductsOfCart(cart.getProductIds())).thenReturn(productsFromInventory);

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

    @Test
    void productsAreNotAvailable() {
        Map<Long, Integer> productQuantities = new HashMap<>() {{
            put(1L, 10);
        }};
        Cart cart = new Cart(1L, "123", productQuantities);
        when(cartQueryPort.getByCustomerId("123")).thenReturn(cart);

        List<ProductDataModel> productsFromInventory = new ArrayList<>() {{
            add(new ProductDataModel(1L, 5, "laptop", 234D));
        }};
        when(productQueryPort.getProductsOfCart(cart.getProductIds())).thenReturn(productsFromInventory);
        verifyNoInteractions(cartPort, paymentPort, paymentSavedEventPublisher);
        assertThrows(PaymentDomainException.class, () -> commandService.execute(command));
    }
}
