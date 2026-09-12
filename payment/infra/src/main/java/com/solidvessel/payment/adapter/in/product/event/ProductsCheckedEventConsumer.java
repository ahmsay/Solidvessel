package com.solidvessel.payment.adapter.in.product.event;

import com.solidvessel.payment.payment.service.UpdatePaymentStatusCommandService;
import com.solidvessel.payment.product.event.ProductsCheckedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductsCheckedEventConsumer {

    private final UpdatePaymentStatusCommandService updatePaymentStatusCommandService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${queues.product.checked}", arguments = {
                    @Argument(name = "x-dead-letter-exchange", value = "${exchanges.dead-letter}"),
                    @Argument(name = "x-dead-letter-routing-key", value = "${queues.product.checked}")
            }),
            exchange = @Exchange(value = "${exchanges.product}", type = "topic"),
            key = "${routing-keys.product.checked}")
    )
    void consume(ProductsCheckedEvent event) {
        updatePaymentStatusCommandService.execute(event);
    }
}
