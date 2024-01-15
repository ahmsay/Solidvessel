package com.solidvessel.payment.adapter.in.product.event;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductAvailableEventConfig {

    @Value("${exchanges.product}")
    private String productTopicName;

    @Value("${queues.payment}")
    private String paymentQueueName;

    @Value("${routing-keys.product.available}")
    private String productAvailableRoutingKey;

    @Bean
    public TopicExchange productTopic() {
        return new TopicExchange(productTopicName);
    }

    @Bean
    public Queue paymentQueue() {
        return new Queue(paymentQueueName);
    }

    @Bean
    Binding productAvailableBinding() {
        return BindingBuilder.bind(paymentQueue()).to(productTopic()).with(productAvailableRoutingKey);
    }
}
