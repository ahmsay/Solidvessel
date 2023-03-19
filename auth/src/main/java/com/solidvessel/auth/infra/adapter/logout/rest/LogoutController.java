package com.solidvessel.auth.infra.adapter.logout.rest;

import com.solidvessel.auth.domain.logout.port.LogoutPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LogoutController {

    private final LogoutPort logoutPort;

    @GetMapping("/logout")
    public void logout() {
        logoutPort.logout();
    }
}
