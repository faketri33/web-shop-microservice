package org.faketri.infastructure.user.controller;

import org.springframework.http.MediaType;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class controller {

    @RequestMapping("/")
    public Mono<String> findMe() {
        return Mono.just("Hello its user service");
    }

    @RequestMapping("/me")
    public Mono<String> findMe(JwtAuthenticationToken token) {
        Jwt jwt = token.getToken();
        String sub = jwt.getSubject();
        String username = jwt.getClaimAsString("preferred_username");
        String email = jwt.getClaimAsString("email");

        System.out.println("ID: " + sub);
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);

        return Mono.just("Профиль создан/обновлён " + username);
    }

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public Mono<String> authTest() {
        return Mono.just("Hello its user service auth");
    }
}
