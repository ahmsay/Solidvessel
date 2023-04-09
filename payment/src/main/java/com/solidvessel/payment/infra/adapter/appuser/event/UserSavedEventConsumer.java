package com.solidvessel.payment.infra.adapter.appuser.event;

import com.solidvessel.payment.domain.appuser.event.UserSavedEvent;
import com.solidvessel.payment.domain.cart.service.command.CreateCartCommand;
import com.solidvessel.shared.domain.service.CommandService;
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
