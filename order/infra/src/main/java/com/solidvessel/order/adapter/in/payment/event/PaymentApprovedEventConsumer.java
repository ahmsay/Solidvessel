package com.solidvessel.order.adapter.in.payment.event;

import com.solidvessel.order.order.service.AddOrderCommandService;
import com.solidvessel.order.payment.event.PaymentApprovedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentApprovedEventConsumer {

    private final AddOrderCommandService addOrderCommandService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${queues.payment.approved}", arguments = {
                    @Argument(name = "x-dead-letter-exchange", value = "${exchanges.dead-letter}"),
                    @Argument(name = "x-dead-letter-routing-key", value = "${queues.payment.approved}")
            }),
            exchange = @Exchange(value = "${exchanges.payment}", type = "topic"),
            key = "${routing-keys.payment.approved}")
    )
    void consume(PaymentApprovedEvent event) {
        addOrderCommandService.execute(event);
    }
}
