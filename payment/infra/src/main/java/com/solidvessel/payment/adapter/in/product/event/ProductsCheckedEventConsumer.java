package com.solidvessel.payment.adapter.in.product.event;

import com.solidvessel.payment.product.event.ProductsCheckedEvent;
import com.solidvessel.shared.service.CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductsCheckedEventConsumer {

    private final CommandService<ProductsCheckedEvent> updatePaymentStatusCommandService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${queues.product.checked}"),
            exchange = @Exchange(value = "${exchanges.product}", type = "topic"),
            key = "${routing-keys.product.checked}")
    )
    void consume(final ProductsCheckedEvent event) {
        updatePaymentStatusCommandService.execute(event);
    }
}
