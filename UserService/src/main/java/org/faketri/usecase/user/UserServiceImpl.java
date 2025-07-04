package org.faketri.usecase.user;

import org.faketri.entity.user.gateway.UserRepository;
import org.faketri.entity.user.model.User;
import org.faketri.infastructure.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public Mono<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Mono<User> save(User user) {
        return userRepository.save(user);
    }
}
