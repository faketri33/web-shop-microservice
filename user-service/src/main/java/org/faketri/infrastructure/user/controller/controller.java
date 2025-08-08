package org.faketri.infrastructure.user.controller;

import org.faketri.entity.user.model.Users;
import org.faketri.entity.user.model.UserLikedProduct;
import org.faketri.infrastructure.user.service.UserLikeProductService;
import org.faketri.infrastructure.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.util.UUID;

@RestController
@RequestMapping(path = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class controller {

    private static final Logger log = LoggerFactory.getLogger(controller.class);
    private final UserService userService;
    private final UserLikeProductService userLikeProductService;

    public controller(UserService userService, UserLikeProductService userLikeProductService) {
        this.userService = userService;
        this.userLikeProductService = userLikeProductService;
    }

    @RequestMapping("/")
    public Mono<String> hello() {
        return Mono.just("Hello its user service");
    }

    @RequestMapping("/profile")
    public Mono<Users> profile(JwtAuthenticationToken token) {
        return userService.findMe(token);
    }

    @RequestMapping(value = "{userId}/favorites", method = RequestMethod.GET)
    public ResponseEntity<Flux<UserLikedProduct>> favorites(@PathVariable UUID userId) {
        return ResponseEntity.ok()
                .body(userLikeProductService.findById(userId));
    }

    @RequestMapping(value = "{userId}/favorites/{productId}", method = RequestMethod.POST)
    public Mono<UserLikedProduct> addToFavorites(@PathVariable UUID userId, @PathVariable UUID productId) {
        return userLikeProductService.addToFavorites(userId, productId);
    }

    @RequestMapping(value = "/favorites", method = RequestMethod.DELETE)
    public void removeToFavorites(@RequestBody UserLikedProduct u) {
        log.info(u.toString());
        userLikeProductService.remove(u);
    }
}
