package com.microshop.orderservice.event;

import com.microshop.orderservice.entity.Order;
import com.microshop.orderservice.services.IOrderService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EventHandler {

    private final IOrderService orderService;

    public EventHandler(IOrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = "${order.queue}")
    void handlePaymentSaved(final PaymentSavedEvent event) {
        try {
            orderService.save(new Order("Preparing", event.getCustomerId(), event.getPaymentId()));
        } catch (final Exception ex) {
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }
}
