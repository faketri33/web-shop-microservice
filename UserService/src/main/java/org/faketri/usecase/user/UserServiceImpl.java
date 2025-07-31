package org.faketri.usecase.user;

import org.faketri.entity.user.gateway.UserRepository;
import org.faketri.entity.user.model.User;
import org.faketri.infastructure.user.service.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public Mono<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Mono<User> registration(User user) {
        return save(user);
    }

    @Override
    public Mono<User> save(User user) {
        return userRepository.save(user);
    }
}
