package com.solidvessel.inventory.infra.adapter.payment.event;

import com.solidvessel.inventory.domain.payment.event.PaymentSavedEvent;
import com.solidvessel.inventory.domain.product.service.ProductCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentSavedEventConsumer {

    private final ProductCommandService productService;

    @RabbitListener(queues = "${queues.product}")
    void consume(final PaymentSavedEvent event) {
        try {
            productService.updateSoldProducts(event.paymentId(), event.productIds());
        } catch (final Exception ex) {
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }
}
