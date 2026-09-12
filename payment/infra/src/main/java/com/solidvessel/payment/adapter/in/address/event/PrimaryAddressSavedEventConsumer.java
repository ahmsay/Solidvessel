package com.solidvessel.payment.adapter.in.address.event;

import com.solidvessel.payment.address.event.PrimaryAddressSavedEvent;
import com.solidvessel.payment.address.service.SaveAddressCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PrimaryAddressSavedEventConsumer {

    private final SaveAddressCommandService saveAddressCommandService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${queues.address.primary-saved}", arguments = {
                    @Argument(name = "x-dead-letter-exchange", value = "${exchanges.dead-letter}"),
                    @Argument(name = "x-dead-letter-routing-key", value = "${queues.address.primary-saved}")
            }),
            exchange = @Exchange(value = "${exchanges.address}", type = "topic"),
            key = "${routing-keys.address.primary-saved}")
    )
    void consume(PrimaryAddressSavedEvent event) {
        saveAddressCommandService.execute(event);
    }
}
