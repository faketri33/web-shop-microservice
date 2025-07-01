package org.faketri.infastructure.user;

import org.springframework.http.MediaType;
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

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public Mono<String> authTest() {
        return Mono.just("Hello its user service auth");
    }
}
