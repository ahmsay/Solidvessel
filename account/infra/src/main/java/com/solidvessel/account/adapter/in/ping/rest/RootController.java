package com.solidvessel.account.adapter.in.ping.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class RootController {

    @PreAuthorize("hasAuthority('CUSTOMER')")
    @GetMapping("/ping")
    public String ping() throws UnknownHostException {
        InetAddress address = InetAddress.getLocalHost();
        return "Account microservice works. Host: %s".formatted(address.getHostName());
    }
}
