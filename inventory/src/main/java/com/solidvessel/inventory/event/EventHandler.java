package com.solidvessel.inventory.event;

import com.solidvessel.inventory.service.ProductService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EventHandler {

    private final ProductService productService;

    public EventHandler(ProductService productService) {
        this.productService = productService;
    }

    @RabbitListener(queues = "${queues.product}")
    void handlePaymentSaved(final PaymentSavedEvent event) {
        try {
            productService.updateSoldProducts(event.paymentId(), event.productIds());
        } catch (final Exception ex) {
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }
}
