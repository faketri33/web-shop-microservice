package org.faketri.usecase.auth;

import org.faketri.entity.profile.model.Profile;
import org.faketri.infrastructure.auth.gateway.AuthService;
import org.faketri.infrastructure.pojo.request.RegisterRequestPojo;
import org.faketri.infrastructure.profile.gateway.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.UUID;

@Service
public class KeycloakAuthServiceImpl implements AuthService {

    private static final Logger log = LoggerFactory.getLogger(KeycloakAuthServiceImpl.class);
    private final ProfileService profileService;
    private final WebClient webClient;
    private final String realm;
    private final String adminClientId;
    private final String adminUsername;
    private final String adminPassword;

    public KeycloakAuthServiceImpl(
            ProfileService profileService, @Value("${keycloak.base-url}") String baseUrl,
            @Value("${keycloak.realm}") String realm,
            @Value("${keycloak.admin.client-id}") String adminClientId,
            @Value("${keycloak.admin.username}") String adminUsername,
            @Value("${keycloak.admin.password}") String adminPassword,
            WebClient.Builder webClientBuilder
    ) {
        this.profileService = profileService;
        this.realm = realm;
        this.adminClientId = adminClientId;
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    private Mono<String> getAdminAccessToken() {
        log.info("Get Admin Access Token");
        String tokenUrl = "/realms/master/protocol/openid-connect/token";

        return webClient.post()
                .uri(tokenUrl)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData("grant_type", "password")
                        .with("client_id", adminClientId)
                        .with("username", adminUsername)
                        .with("password", adminPassword))
                .retrieve()
                .bodyToMono(Map.class)
                .map(map -> (String) map.get("access_token"));
    }

    @Override
    public Mono<Void> register(RegisterRequestPojo req) {
        return getAdminAccessToken()
                .flatMap(adminToken -> createUserInKeycloak(adminToken, buildCreatePayload(req)))
                .flatMap(userId -> persistProfile(userId, req))
                .doOnSuccess(v -> log.info("User {} registered successfully", req.username()))
                .doOnError(ex -> log.error("Failed to register user {}: {}", req.username(), ex.getMessage()))
                .then();
    }

    private Map<String, Object> buildCreatePayload(RegisterRequestPojo req) {
        return Map.of(
                "username", req.username(),
                "email", req.email(),
                "credentials", req.credentials(),
                "enabled", true,
                "firstName", req.firstname(),
                "lastName", req.lastname()
        );
    }

    private Mono<String> createUserInKeycloak(String adminToken, Map<String, Object> payload) {
        String createUrl = String.format("/admin/realms/%s/users", realm);

        return webClient.post()
                .uri(createUrl)
                .header("Authorization", "Bearer " + adminToken)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(payload)
                .exchangeToMono(resp -> {
                    int status = resp.statusCode().value();
                    if (status == 201) {
                        String location = resp.headers().asHttpHeaders().getFirst("Location");
                        String id = extractIdFromLocation(location);
                        if (id == null) {
                            return Mono.error(new RuntimeException("User created but Location header missing or malformed"));
                        }
                        return Mono.just(id);
                    }
                    if (status == 409) {
                        return Mono.error(new IllegalStateException("User already exists"));
                    }
                    return resp.bodyToMono(String.class)
                            .defaultIfEmpty("<empty>")
                            .flatMap(body -> Mono.error(new RuntimeException("Create user failed: " + resp.statusCode() + " : " + body)));
                });
    }

    private Mono<Void> persistProfile(String userId, RegisterRequestPojo req) {
        UUID uuid;
        try {
            uuid = UUID.fromString(userId);
        } catch (IllegalArgumentException e) {
            return Mono.error(new RuntimeException("Invalid userId format: " + userId, e));
        }

        Profile profile = new Profile(uuid, req.email(), req.username(), null);
        return profileService.save(profile).then();
    }

    private String extractIdFromLocation(String location) {
        if (location == null || location.isBlank()) return null;
        int idx = location.lastIndexOf('/');
        return (idx >= 0 && idx < location.length() - 1) ? location.substring(idx + 1) : null;
    }
}
