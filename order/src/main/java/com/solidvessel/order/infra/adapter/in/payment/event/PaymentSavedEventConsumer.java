package com.solidvessel.order.infra.adapter.in.payment.event;

import com.solidvessel.order.domain.order.service.command.AddOrderCommand;
import com.solidvessel.order.domain.payment.event.PaymentSavedEvent;
import com.solidvessel.shared.domain.service.CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentSavedEventConsumer {

    private final CommandService<AddOrderCommand> addOrderCommandService;

    @RabbitListener(queues = "${queues.order}")
    void consume(final PaymentSavedEvent event) {
        try {
            addOrderCommandService.execute(event.toCommand());
        } catch (final Exception ex) {
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }
}
