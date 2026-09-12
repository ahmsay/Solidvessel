package com.solidvessel.payment.configuration;

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
            @Value("${queues.product.available}") String productAvailableQueueName,
            @Value("${queues.product.available-dlq}") String productAvailableDlqName,
            @Value("${queues.product.checked}") String productsCheckedQueueName,
            @Value("${queues.product.checked-dlq}") String productsCheckedDlqName,
            @Value("${queues.address.primary-saved}") String primaryAddressSavedQueueName,
            @Value("${queues.address.primary-saved-dlq}") String primaryAddressSavedDlqName) {
        var deadLetterExchange = new DirectExchange(deadLetterExchangeName);
        var productAvailableDlq = new Queue(productAvailableDlqName);
        var productsCheckedDlq = new Queue(productsCheckedDlqName);
        var primaryAddressSavedDlq = new Queue(primaryAddressSavedDlqName);
        Binding productAvailableBinding = BindingBuilder.bind(productAvailableDlq)
                .to(deadLetterExchange)
                .with(productAvailableQueueName);
        Binding productsCheckedBinding = BindingBuilder.bind(productsCheckedDlq)
                .to(deadLetterExchange)
                .with(productsCheckedQueueName);
        Binding primaryAddressSavedBinding = BindingBuilder.bind(primaryAddressSavedDlq)
                .to(deadLetterExchange)
                .with(primaryAddressSavedQueueName);
        return new Declarables(
                deadLetterExchange,
                productAvailableDlq,
                productsCheckedDlq,
                primaryAddressSavedDlq,
                productAvailableBinding,
                productsCheckedBinding,
                primaryAddressSavedBinding);
    }
}
