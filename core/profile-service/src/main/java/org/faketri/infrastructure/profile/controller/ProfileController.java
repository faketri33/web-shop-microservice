package org.faketri.infrastructure.profile.controller;

import org.faketri.entity.profile.model.ProfileLikedProduct;
import org.faketri.infrastructure.pojo.mapper.ProfileMapper;
import org.faketri.infrastructure.pojo.response.ProfileResponsePojo;
import org.faketri.infrastructure.profile.gateway.ProfileService;
import org.faketri.infrastructure.profile.gateway.UserLikeProductService;
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
@RequestMapping(path = "/api/profile", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {

    private static final Logger log = LoggerFactory.getLogger(ProfileController.class);
    private final ProfileService profileService;
    private final UserLikeProductService likeProductService;

    public ProfileController(ProfileService profileService, UserLikeProductService likeProductService) {
        this.profileService = profileService;
        this.likeProductService = likeProductService;
    }

    @GetMapping("/")
    public Mono<ProfileResponsePojo> profile(JwtAuthenticationToken token) {
        return profileService.findMe(token).flatMap(ProfileMapper::toPojo);
    }

    @GetMapping(value = "/favorites")
    public ResponseEntity<Flux<ProfileLikedProduct>> favorites(JwtAuthenticationToken token) {
        return ResponseEntity.ok()
                .body(likeProductService.findById(userId(token)));
    }

    @PostMapping(value = "/favorites/{productId}")
    public Mono<ProfileLikedProduct> addToFavorites(JwtAuthenticationToken token, @PathVariable UUID productId) {
        return likeProductService.addToFavorites(userId(token), productId);
    }

    @DeleteMapping(value = "/favorites")
    public void removeToFavorites(JwtAuthenticationToken token, @RequestBody ProfileLikedProduct u) {
        likeProductService.remove(userId(token), u);
    }

    private UUID userId(JwtAuthenticationToken token) {
        return UUID.fromString(token.getToken().getSubject());
    }
}
