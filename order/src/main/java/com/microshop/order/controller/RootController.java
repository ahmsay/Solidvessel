package com.microshop.order.controller;

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
            return "Order microservice works. Host: %s".formatted(address.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "Unknown host.";
    }
}
