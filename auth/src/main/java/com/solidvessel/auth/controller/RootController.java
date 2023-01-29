package com.solidvessel.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class RootController {

    @GetMapping("/")
    public String ping() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            return "Authentication microservice works. Host: %s".formatted(address.getHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "Unknown host.";
    }
}
