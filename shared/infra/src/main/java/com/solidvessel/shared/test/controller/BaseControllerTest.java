package com.solidvessel.shared.test.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solidvessel.shared.Profiles;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

@ActiveProfiles(Profiles.INTEGRATION_TEST)
@Import(TestSecurityFilterConfig.class)
public class BaseControllerTest {

    ObjectMapper objectMapper = new ObjectMapper();

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
