package com.microshop.gatewayservice.controllers;

import com.microshop.gatewayservice.configuration.BaseUrlProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeRestController {

    private final BaseUrlProperties properties;

    public HomeRestController(BaseUrlProperties properties) {
        this.properties = properties;
    }

    @GetMapping("/")
    public String testBaseUrl() {
        return properties.getMessage();
    }
}
