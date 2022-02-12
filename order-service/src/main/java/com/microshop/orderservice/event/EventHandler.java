package com.microshop.orderservice.event;

import com.microshop.orderservice.entity.Order;
import com.microshop.orderservice.service.OrderService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EventHandler {

    private final OrderService orderService;

    public EventHandler(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = "${queues.order}")
    void handlePaymentSaved(final PaymentSavedEvent event) {
        try {
            orderService.add(new Order("Preparing", event.customerId(), event.paymentId()));
        } catch (final Exception ex) {
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }
}
