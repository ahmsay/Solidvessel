package com.solidvessel.shared.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.boot.cache.autoconfigure.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.List;

@Configuration
public class RedisCacheConfigForUsers {

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizerForUsers() {
        ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        return (builder) -> builder
                .withCacheConfiguration("users",
                        RedisCacheConfiguration.defaultCacheConfig()
                                .entryTtl(Duration.ofMinutes(5))
                                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
                                        new JacksonJsonRedisSerializer<>(objectMapper, List.class))))
                .withCacheConfiguration("user",
                        RedisCacheConfiguration.defaultCacheConfig()
                                .entryTtl(Duration.ofMinutes(30))
                                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
                                        new JacksonJsonRedisSerializer<>(objectMapper, UserRepresentation.class))));
    }
}
