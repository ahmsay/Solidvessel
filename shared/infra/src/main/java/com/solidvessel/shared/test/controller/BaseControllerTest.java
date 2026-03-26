package com.solidvessel.shared.test.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.solidvessel.shared.Profiles;
import jakarta.annotation.PostConstruct;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@ActiveProfiles(Profiles.INTEGRATION_TEST)
@Import(TestSecurityFilterConfig.class)
@ComponentScan(
        basePackages = "com.solidvessel",
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Mapper.class})}
)
public class BaseControllerTest {

    ObjectMapper objectMapper;

    @PostConstruct
    void configureObjectMapper() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public String bodyOf(final MvcResult mvcResult) {
        try {
            return mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        } catch (UnsupportedEncodingException exception) {
            throw new RuntimeException(exception);
        }
    }

    public String bodyOf(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}
