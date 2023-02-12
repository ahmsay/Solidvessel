package com.solidvessel.account.infra.adapter.customer.event;

import com.solidvessel.account.domain.appuser.event.UserSavedEvent;
import com.solidvessel.account.domain.customer.model.Customer;
import com.solidvessel.account.domain.customer.service.CustomerService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CustomerEventConsumer {

    private final CustomerService customerService;

    public CustomerEventConsumer(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RabbitListener(queues = "${queues.customer}")
    void handleUserSavedEvent(final UserSavedEvent event) {
        try {
            customerService.add(new Customer(event.userId(), event.firstName(), event.lastName()));
        } catch (final Exception ex) {
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }
}
