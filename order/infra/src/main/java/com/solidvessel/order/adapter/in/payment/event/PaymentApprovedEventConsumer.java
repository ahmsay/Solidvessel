package com.solidvessel.order.adapter.in.payment.event;

import com.solidvessel.order.order.service.AddOrderCommandService;
import com.solidvessel.order.payment.event.PaymentApprovedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentApprovedEventConsumer {

    private final AddOrderCommandService addOrderCommandService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${queues.payment.approved}"),
            exchange = @Exchange(value = "${exchanges.payment}", type = "topic"),
            key = "${routing-keys.payment.approved}")
    )
    void consume(PaymentApprovedEvent event) {
        addOrderCommandService.execute(event);
    }
}
