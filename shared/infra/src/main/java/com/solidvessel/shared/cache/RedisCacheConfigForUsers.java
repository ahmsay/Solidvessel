package com.solidvessel.shared.cache;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.springframework.boot.cache.autoconfigure.RedisCacheManagerBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import tools.jackson.databind.DefaultTyping;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.jsontype.BasicPolymorphicTypeValidator;

import java.time.Duration;

@Configuration
public class RedisCacheConfigForUsers {

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizerForUsers() {
        ObjectMapper objectMapper = JsonMapper.builder()
                .activateDefaultTyping(
                        BasicPolymorphicTypeValidator.builder().allowIfBaseType(Object.class).build(),
                        DefaultTyping.NON_FINAL,
                        JsonTypeInfo.As.PROPERTY)
                .build();
        GenericJacksonJsonRedisSerializer serializer = new GenericJacksonJsonRedisSerializer(objectMapper);

        return (builder) -> builder
                .withCacheConfiguration("users",
                        RedisCacheConfiguration.defaultCacheConfig()
                                .entryTtl(Duration.ofMinutes(5))
                                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer)))
                .withCacheConfiguration("user",
                        RedisCacheConfiguration.defaultCacheConfig()
                                .entryTtl(Duration.ofMinutes(30))
                                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(serializer)));
    }
}
