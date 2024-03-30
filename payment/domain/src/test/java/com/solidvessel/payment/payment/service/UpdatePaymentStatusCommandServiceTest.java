package com.solidvessel.payment.payment.service;

import com.solidvessel.payment.payment.event.PaymentApprovedEvent;
import com.solidvessel.payment.payment.model.Payment;
import com.solidvessel.payment.payment.model.PaymentStatus;
import com.solidvessel.payment.payment.port.PaymentPort;
import com.solidvessel.payment.payment.port.PaymentQueryPort;
import com.solidvessel.payment.product.event.ProductsCheckedEvent;
import com.solidvessel.payment.product.model.Product;
import com.solidvessel.payment.product.model.ProductCategory;
import com.solidvessel.shared.event.EventPublisher;
import com.solidvessel.shared.test.BaseUnitTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UpdatePaymentStatusCommandServiceTest extends BaseUnitTest {

    @Mock
    private PaymentQueryPort paymentQueryPort;

    @Mock
    private PaymentPort paymentPort;

    @Mock
    private EventPublisher<PaymentApprovedEvent> paymentApprovedEventPublisher;

    @Test
    void approvePayment() {
        var event = new ProductsCheckedEvent(1L, true, "123");
        var commandService = new UpdatePaymentStatusCommandService(paymentQueryPort, paymentPort, paymentApprovedEventPublisher);
        var payment = createPayment();
        when(paymentQueryPort.getById(1L)).thenReturn(payment);
        commandService.execute(event);
        assertEquals(PaymentStatus.APPROVED, payment.getStatus());
        verify(paymentPort).save(payment);
        verify(paymentApprovedEventPublisher).publish(any(PaymentApprovedEvent.class));
    }

    @Test
    void cancelPayment() {
        var event = new ProductsCheckedEvent(1L, false, "123");
        var commandService = new UpdatePaymentStatusCommandService(paymentQueryPort, paymentPort, paymentApprovedEventPublisher);
        var payment = createPayment();
        when(paymentQueryPort.getById(1L)).thenReturn(payment);
        commandService.execute(event);
        assertEquals(PaymentStatus.CANCELLED, payment.getStatus());
        verifyNoInteractions(paymentApprovedEventPublisher);
    }

    private Payment createPayment() {
        List<Product> products = new ArrayList<>() {{
            add(new Product(4L, "chair", 15D, ProductCategory.FURNITURE, 1));
            add(new Product(1L, "apple", 3D, ProductCategory.ELECTRONICS, 3));
        }};
        return new Payment("123", products, 24D, PaymentStatus.PENDING);
    }
}
