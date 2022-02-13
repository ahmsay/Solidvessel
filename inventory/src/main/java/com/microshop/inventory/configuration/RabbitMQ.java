package com.microshop.inventory.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQ {

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
    public Queue productQueue() {
        return new Queue(productQueueName);
    }

    @Bean
    Binding paymentSavedBinding() {
        return BindingBuilder.bind(productQueue()).to(paymentTopic()).with(paymentSavedRoutingKey);
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        return new Jackson2JsonMessageConverter(mapper);
    }
}
