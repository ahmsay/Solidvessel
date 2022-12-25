package com.microshop.account.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class RootController {

    @GetMapping("/ping")
    public String ping() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            return "Account microservice works. Host: %s".formatted(address.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "Unknown host.";
    }
}
