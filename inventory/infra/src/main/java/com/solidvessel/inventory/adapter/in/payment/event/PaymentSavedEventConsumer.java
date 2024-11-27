package com.solidvessel.inventory.adapter.in.payment.event;

import com.solidvessel.inventory.payment.event.PaymentSavedEvent;
import com.solidvessel.inventory.product.service.UpdateProductQuantitiesCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentSavedEventConsumer {

    private final UpdateProductQuantitiesCommandService updateProductQuantitiesCommandService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${queues.payment.saved}"),
            exchange = @Exchange(value = "${exchanges.payment}", type = "topic"),
            key = "${routing-keys.payment.saved}")
    )
    void consume(PaymentSavedEvent event) {
        updateProductQuantitiesCommandService.execute(event);
    }
}
