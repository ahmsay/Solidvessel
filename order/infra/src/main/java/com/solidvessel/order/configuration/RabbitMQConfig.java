package com.solidvessel.order.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.json.JsonMapper;

@Configuration
public class RabbitMQConfig {

    @Bean
    public JacksonJsonMessageConverter converter() {
        return new JacksonJsonMessageConverter(new JsonMapper());
    }

    @Bean
    public Declarables deadLetterTopology(
            @Value("${exchanges.dead-letter}") String deadLetterExchangeName,
            @Value("${queues.payment.approved}") String paymentApprovedQueueName,
            @Value("${queues.payment.approved-dlq}") String paymentApprovedDlqName) {
        var deadLetterExchange = new DirectExchange(deadLetterExchangeName);
        var deadLetterQueue = new Queue(paymentApprovedDlqName);
        Binding binding = BindingBuilder.bind(deadLetterQueue)
                .to(deadLetterExchange)
                .with(paymentApprovedQueueName);
        return new Declarables(deadLetterExchange, deadLetterQueue, binding);
    }
}
