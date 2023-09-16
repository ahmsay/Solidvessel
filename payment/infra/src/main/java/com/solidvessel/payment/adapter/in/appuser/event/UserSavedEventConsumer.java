package com.solidvessel.payment.adapter.in.appuser.event;

import com.solidvessel.payment.appuser.event.UserSavedEvent;
import com.solidvessel.payment.cart.service.command.CreateCartCommand;
import com.solidvessel.shared.service.CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSavedEventConsumer {

    private final CommandService<CreateCartCommand> createCartCommandService;

    @RabbitListener(queues = "${queues.cart}")
    void consume(final UserSavedEvent event) {
        try {
            createCartCommandService.execute(event.toCommand());
        } catch (final Exception ex) {
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }
}
