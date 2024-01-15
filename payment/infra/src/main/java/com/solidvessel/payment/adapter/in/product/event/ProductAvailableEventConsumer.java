package com.solidvessel.payment.adapter.in.product.event;

import com.solidvessel.payment.cart.service.command.AddToCartCommand;
import com.solidvessel.payment.product.event.ProductAvailableEvent;
import com.solidvessel.shared.service.CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductAvailableEventConsumer {

    private final CommandService<AddToCartCommand> addToCartCommandService;

    @RabbitListener(queues = "${queues.payment}")
    void consume(final ProductAvailableEvent event) {
        try {
            addToCartCommandService.execute(event.toCommand());
        } catch (final Exception ex) {
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }
}
