package com.microshop.account.event;

import com.microshop.account.entity.Customer;
import com.microshop.account.service.CustomerService;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EventHandler {

    private final CustomerService customerService;

    public EventHandler(CustomerService customerService) {
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
