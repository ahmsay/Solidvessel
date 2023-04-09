package com.solidvessel.account.infra.adapter.appuser.event;

import com.solidvessel.account.domain.appuser.event.UserSavedEvent;
import com.solidvessel.account.domain.customer.service.command.AddCustomerCommand;
import com.solidvessel.shared.domain.service.CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSavedEventConsumer {

    private final CommandService<AddCustomerCommand> addCustomerCommandService;

    @RabbitListener(queues = "${queues.customer}")
    void consume(final UserSavedEvent event) {
        try {
            addCustomerCommandService.execute(event.toCommand());
        } catch (final Exception ex) {
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }
}
