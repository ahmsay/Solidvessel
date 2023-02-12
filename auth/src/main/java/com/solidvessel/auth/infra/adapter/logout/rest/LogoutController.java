package com.solidvessel.auth.infra.adapter.logout.rest;

import com.solidvessel.auth.domain.logout.port.LogoutPort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogoutController {

    private final LogoutPort logoutPort;

    public LogoutController(LogoutPort logoutPort) {
        this.logoutPort = logoutPort;
    }

    @GetMapping("/logout")
    public void logout() {
        logoutPort.logout();
    }
}
