package com.solidvessel.inventory.adapter.in.payment.event;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentSavedEventConfig {

    @Value("${exchanges.payment}")
    private String paymentTopicName;

    @Value("${queues.product}")
    private String productQueueName;

    @Value("${routing-keys.payment.saved}")
    private String paymentSavedRoutingKey;

    @Bean
    public TopicExchange paymentTopic() {
        return new TopicExchange(paymentTopicName);
    }

    @Bean
    public Queue orderQueue() {
        return new Queue(productQueueName);
    }

    @Bean
    Binding paymentSavedBinding() {
        return BindingBuilder.bind(orderQueue()).to(paymentTopic()).with(paymentSavedRoutingKey);
    }
}
