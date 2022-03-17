package com.microshop.account.configuration;

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

    @Value("${exchanges.signUp}")
    private String signUpTopicName;

    @Value("${queues.customer}")
    private String customerQueueName;

    @Value("${routing-keys.user.saved}")
    private String userSavedRoutingKey;

    @Bean
    public TopicExchange signUpTopic() {
        return new TopicExchange(signUpTopicName);
    }

    @Bean
    public Queue customerQueue() {
        return new Queue(customerQueueName);
    }

    @Bean
    Binding userSavedBinding() {
        return BindingBuilder.bind(customerQueue()).to(signUpTopic()).with(userSavedRoutingKey);
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        ObjectMapper mapper = new ObjectMapper().findAndRegisterModules();
        return new Jackson2JsonMessageConverter(mapper);
    }
}
