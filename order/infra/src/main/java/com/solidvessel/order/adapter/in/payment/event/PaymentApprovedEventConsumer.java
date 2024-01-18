package com.solidvessel.order.adapter.in.payment.event;

import com.solidvessel.order.order.service.command.AddOrderCommand;
import com.solidvessel.order.payment.event.PaymentApprovedEvent;
import com.solidvessel.shared.service.CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentApprovedEventConsumer {

    private final CommandService<AddOrderCommand> addOrderCommandService;

    @RabbitListener(queues = "${queues.order}")
    void consume(final PaymentApprovedEvent event) {
        try {
            addOrderCommandService.execute(event.toCommand());
        } catch (final Exception ex) {
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }
}
