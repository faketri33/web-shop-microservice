package org.faketri.usecase.user;

import jakarta.ws.rs.NotFoundException;
import org.faketri.entity.user.exception.UserSavingError;
import org.faketri.entity.user.gateway.UserRepository;
import org.faketri.entity.user.model.Users;
import org.faketri.infastructure.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        log.debug("start UserServiceImpl + {}", LocalDateTime.now());
        this.userRepository = userRepository;
    }

    @Override
    public Mono<Users> findById(UUID id) {
        log.debug("fetch user with id - {}", id);
        return userRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException(String.format("User with id - %s not found", id))));
    }

    @Override
    public Mono<Users> findByUsername(String username) {
        log.debug("fetch user with username - {}", username);
        return userRepository.findByUsername(username)
                .switchIfEmpty(Mono.error(new NotFoundException(String.format("User with username - %s not found", username))));
    }

    @Override
    public Mono<Users> findMe(Users user) {
        log.debug("saving user - {}", user);
        return findByUsername(user.getUsername())
                .switchIfEmpty(save(user))
                .onErrorMap(e -> {
                    log.error("User save error - {}", e.getMessage());
                    return new UserSavingError(e.getMessage());
                });
    }

    @Override
    public Mono<Users> findMe(JwtAuthenticationToken token) {
        return findMe(extractFromToken(token));
    }

    @Override
    public Mono<Users> save(Users user) {
        return userRepository.save(user);
    }

    @Override
    public Users extractFromToken(JwtAuthenticationToken token) {
        final Jwt jwt = token.getToken();
        final String username = jwt.getClaimAsString("preferred_username");
        final String email = jwt.getClaimAsString("email");

        Users user = new Users();
        user.setEmail(email);
        user.setUsername(username);
        return user;
    }
}
