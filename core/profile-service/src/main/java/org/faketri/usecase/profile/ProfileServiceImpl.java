package org.faketri.usecase.profile;

import org.faketri.entity.profile.exception.UserSavingError;
import org.faketri.entity.profile.gateway.ProfileRepository;
import org.faketri.entity.profile.model.Profile;
import org.faketri.infrastructure.profile.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ProfileServiceImpl implements ProfileService {

    private static final Logger log = LoggerFactory.getLogger(ProfileServiceImpl.class);
    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        log.debug("start ProfileServiceImpl + {}", LocalDateTime.now());
        this.profileRepository = profileRepository;
    }

    @Override
    public Mono<Profile> findById(UUID id) {
        log.debug("fetch user with id - {}", id);
        return profileRepository.findById(id);
    }

    @Override
    public Mono<Profile> findByUsername(String username) {
        log.debug("fetch user with username - {}", username);
        return profileRepository.findByUsername(username);
    }

    @Override
    public Mono<Profile> findMe(Profile user) {
        log.debug("saving user - {}", user);
        return findById(user.getId())
                .switchIfEmpty(insert(user))
                .onErrorMap(e -> {
                    log.info(user.toString());
                    log.error("User save error - {}", e.getMessage());
                    return new UserSavingError(e.getMessage());
                });
    }

    @Override
    public Mono<Profile> findMe(JwtAuthenticationToken token) {
        return findMe(extractFromToken(token));
    }

    @Override
    public Mono<Profile> save(Profile user) {
        return profileRepository.save(user);
    }

    @Override
    public Profile extractFromToken(JwtAuthenticationToken token) {
        final Jwt jwt = token.getToken();
        final String username = jwt.getClaimAsString("preferred_username");
        final String email = jwt.getClaimAsString("email");

        Profile user = new Profile();
        user.setId(UUID.fromString(jwt.getSubject()));
        user.setEmail(email);
        user.setUsername(username);

        return user;
    }

    @Override
    public Mono<Profile> insert(Profile user) {
        return profileRepository.insert(user)
                    .doOnNext(u -> log.info("User inserted successfully - {}", u))
                    .doOnError(e -> log.error("Error inserting user - {}", e.getMessage()));
    }
}
