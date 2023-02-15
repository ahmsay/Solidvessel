package com.solidvessel.order.infra.adapter.payment.event;

import com.solidvessel.order.domain.order.model.Order;
import com.solidvessel.order.domain.order.service.OrderCommandService;
import com.solidvessel.order.domain.payment.event.PaymentSavedEvent;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventConsumer {

    private final OrderCommandService orderCommandService;

    public PaymentEventConsumer(OrderCommandService orderCommandService) {
        this.orderCommandService = orderCommandService;
    }

    @RabbitListener(queues = "${queues.order}")
    void handlePaymentSaved(final PaymentSavedEvent event) {
        try {
            orderCommandService.add(new Order("Preparing", event.customerId(), event.paymentId()));
        } catch (final Exception ex) {
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }
}
