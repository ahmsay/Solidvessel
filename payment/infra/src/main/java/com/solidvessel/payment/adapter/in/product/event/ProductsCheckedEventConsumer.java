package com.solidvessel.payment.adapter.in.product.event;

import com.solidvessel.payment.payment.service.UpdatePaymentStatusCommand;
import com.solidvessel.payment.product.event.ProductsCheckedEvent;
import com.solidvessel.shared.service.CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductsCheckedEventConsumer {

    private final CommandService<UpdatePaymentStatusCommand> updatePaymentStatusCommandService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${queues.product.checked}"),
            exchange = @Exchange(value = "${exchanges.product}", type = "topic"),
            key = "${routing-keys.product.checked}")
    )
    void consume(final ProductsCheckedEvent event) {
        try {
            updatePaymentStatusCommandService.execute(event.toCommand());
        } catch (final Exception ex) {
            throw new AmqpRejectAndDontRequeueException(ex);
        }
    }
}
