package com.solidvessel.payment.adapter.in.product.event;

import com.solidvessel.payment.cart.service.AddToCartCommandService;
import com.solidvessel.payment.product.event.ProductAvailableEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductAvailableEventConsumer {

    private final AddToCartCommandService addToCartCommandService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${queues.product.available}"),
            exchange = @Exchange(value = "${exchanges.product}", type = "topic"),
            key = "${routing-keys.product.available}")
    )
    void consume(ProductAvailableEvent event) {
        addToCartCommandService.execute(event);
    }
}
