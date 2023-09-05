package com.solidvessel.inventory.infra.adapter.in.payment.event;

import com.solidvessel.inventory.domain.payment.event.PaymentSavedEvent;
import com.solidvessel.inventory.domain.product.service.command.UpdateProductQuantitiesCommand;
import com.solidvessel.shared.domain.service.CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentSavedEventConsumer {

    private final CommandService<UpdateProductQuantitiesCommand> updateProductQuantitiesCommandService;

    @RabbitListener(queues = "${queues.product}")
    void consume(final PaymentSavedEvent event) {
        try {
            updateProductQuantitiesCommandService.execute(event.toCommand());
        } catch (final Exception ex) {
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }
}
