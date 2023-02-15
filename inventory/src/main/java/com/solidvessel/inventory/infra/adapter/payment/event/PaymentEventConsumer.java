package com.solidvessel.inventory.infra.adapter.payment.event;

import com.solidvessel.inventory.domain.payment.event.PaymentSavedEvent;
import com.solidvessel.inventory.domain.product.service.ProductCommandService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventConsumer {

    private final ProductCommandService productService;

    public PaymentEventConsumer(ProductCommandService productService) {
        this.productService = productService;
    }

    @RabbitListener(queues = "${queues.product}")
    void consumePaymentSaved(final PaymentSavedEvent event) {
        try {
            productService.updateSoldProducts(event.paymentId(), event.productIds());
        } catch (final Exception ex) {
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }
}
