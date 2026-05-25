package com.solidvessel.order.configuration;

import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tools.jackson.databind.json.JsonMapper;

@Configuration
public class RabbitMQConfig {

    @Bean
    public JacksonJsonMessageConverter converter() {
        return new JacksonJsonMessageConverter(new JsonMapper());
    }
}
