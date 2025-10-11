package org.faketri.infrastructure.auth.controller;

import org.faketri.infrastructure.auth.gateway.AuthService;
import org.faketri.infrastructure.pojo.request.RegisterRequestPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public Mono<Void> register(@RequestBody RegisterRequestPojo request) {
        log.info("RegisterRequestPojo request={}", request);
        return authService.register(request);
    }
}
