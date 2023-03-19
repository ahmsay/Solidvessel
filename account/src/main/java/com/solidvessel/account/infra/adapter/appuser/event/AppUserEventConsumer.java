package com.solidvessel.account.infra.adapter.appuser.event;

import com.solidvessel.account.domain.appuser.event.UserSavedEvent;
import com.solidvessel.account.domain.customer.model.Customer;
import com.solidvessel.account.domain.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppUserEventConsumer {

    private final CustomerService customerService;

    @RabbitListener(queues = "${queues.customer}")
    void consumeUserSavedEvent(final UserSavedEvent event) {
        try {
            customerService.add(new Customer(event.userId(), event.firstName(), event.lastName()));
        } catch (final Exception ex) {
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }
}
