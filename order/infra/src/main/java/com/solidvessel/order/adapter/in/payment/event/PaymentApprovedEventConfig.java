package com.solidvessel.order.adapter.in.payment.event;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentApprovedEventConfig {

    @Value("${exchanges.payment}")
    private String paymentTopicName;

    @Value("${queues.order}")
    private String orderQueueName;

    @Value("${routing-keys.payment.approved}")
    private String paymentApprovedRoutingKey;

    @Bean
    public TopicExchange paymentTopic() {
        return new TopicExchange(paymentTopicName);
    }

    @Bean
    public Queue orderQueue() {
        return new Queue(orderQueueName);
    }

    @Bean
    Binding paymentApprovedBinding() {
        return BindingBuilder.bind(orderQueue()).to(paymentTopic()).with(paymentApprovedRoutingKey);
    }
}
