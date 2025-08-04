package org.faketri.usecase.user;

import org.faketri.entity.user.gateway.UserLikedProductRepository;
import org.faketri.entity.user.model.UserLikedProduct;
import org.faketri.infastructure.user.service.UserLikeProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
public class UserLikeProductServiceImpl implements UserLikeProductService {

    private static final Logger log = LoggerFactory.getLogger(UserLikeProductServiceImpl.class);
    private final UserLikedProductRepository userLikedProductRepository;

    public UserLikeProductServiceImpl(UserLikedProductRepository userLikedProductRepository) {
        this.userLikedProductRepository = userLikedProductRepository;
    }

    @Override
    public Flux<UserLikedProduct> findById(UUID uuid) {
        return userLikedProductRepository.findByUserId(uuid);
    }

    @Override
    public Mono<UserLikedProduct> findByUserIdAndProductId(UUID userId, UUID productId) {
        return userLikedProductRepository.findByUserIdAndProductId(userId, productId);
    }

    @Override
    public Mono<UserLikedProduct> addToFavorites(UUID userId, UUID productId) {
        log.info(String.format("%s - user id %s", getClass().getClass(), userId.toString()));
        return save(new UserLikedProduct(userId, productId));
    }

    @Override
    public Mono<UserLikedProduct> save(UserLikedProduct u) {
        log.info("save user like - " + u);
        return userLikedProductRepository.save(u);
    }

    @Override
    public void remove(UserLikedProduct u) {
        userLikedProductRepository.delete(u);
    }
}
