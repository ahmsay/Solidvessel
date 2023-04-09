package com.solidvessel.payment.infra.adapter.appuser.event;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserSavedEventConfig {

    @Value("${exchanges.signUp}")
    private String signUpTopicName;

    @Value("${queues.cart}")
    private String cartQueueName;

    @Value("${routing-keys.user.saved}")
    private String userSavedRoutingKey;

    @Bean
    public TopicExchange signUpTopic() {
        return new TopicExchange(signUpTopicName);
    }

    @Bean
    public Queue cartQueue() {
        return new Queue(cartQueueName);
    }

    @Bean
    Binding userSavedBinding() {
        return BindingBuilder.bind(cartQueue()).to(signUpTopic()).with(userSavedRoutingKey);
    }
}
