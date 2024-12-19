package com.solidvessel.payment.adapter.in.address.event;

import com.solidvessel.payment.address.event.PrimaryAddressSavedEvent;
import com.solidvessel.payment.address.service.SaveAddressCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PrimaryAddressSavedEventConsumer {

    private final SaveAddressCommandService saveAddressCommandService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${queues.address.primary-saved}"),
            exchange = @Exchange(value = "${exchanges.address}", type = "topic"),
            key = "${routing-keys.address.primary-saved}")
    )
    void consume(PrimaryAddressSavedEvent event) {
        saveAddressCommandService.execute(event);
    }
}
