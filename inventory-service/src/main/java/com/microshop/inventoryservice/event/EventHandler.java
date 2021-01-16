package com.microshop.inventoryservice.event;

import com.microshop.inventoryservice.services.IProductService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EventHandler {

    private final IProductService productService;

    public EventHandler(final IProductService productService) {
        this.productService = productService;
    }

    @RabbitListener(queues = "${product.queue}")
    void handlePaymentSaved(final PaymentSavedEvent event) {
        try {
            productService.setPaymentIds(event.getPaymentId(), event.getProductIds());
        } catch (final Exception e) {
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
