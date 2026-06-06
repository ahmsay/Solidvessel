package com.solidvessel.shared.cache;

import org.springframework.boot.cache.autoconfigure.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import tools.jackson.databind.ObjectMapper;

import java.time.Duration;

@Configuration
public class RedisCacheConfigForUsers {

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizerForUsers() {
        ObjectMapper objectMapper = new ObjectMapper();
        /*objectMapper.activateDefaultTyping(
                LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY
        );*/

        return (builder) -> builder
                .withCacheConfiguration("users",
                        RedisCacheConfiguration.defaultCacheConfig()
                                .entryTtl(Duration.ofMinutes(5))
                                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJacksonJsonRedisSerializer(objectMapper))))
                .withCacheConfiguration("user",
                        RedisCacheConfiguration.defaultCacheConfig()
                                .entryTtl(Duration.ofMinutes(30))
                                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJacksonJsonRedisSerializer(objectMapper))));
    }
}
