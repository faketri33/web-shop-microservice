package org.faketri.usecase.user;

import org.faketri.entity.user.gateway.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserDetailsServiceImpl implements ReactiveUserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).map(u -> new User(
                u.getUsername(),
                u.getPassword(),
                u.getRoles()
                        .stream()
                        .map(x -> new SimpleGrantedAuthority(x.name()))
                        .toList()
        ));
    }
}
