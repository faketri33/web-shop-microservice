package org.faketri.usecase.profile;

import org.faketri.entity.profile.gateway.ProfileLikedProductRepository;
import org.faketri.entity.profile.model.ProfileLikedProduct;
import org.faketri.infrastructure.profile.service.UserLikeProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class UserLikeProductServiceImpl implements UserLikeProductService {

    private static final Logger log = LoggerFactory.getLogger(UserLikeProductServiceImpl.class);
    private final ProfileLikedProductRepository profileLikedProduct;

    public UserLikeProductServiceImpl(ProfileLikedProductRepository profileLikedProduct) {
        this.profileLikedProduct = profileLikedProduct;
    }

    @Override
    public Flux<ProfileLikedProduct> findById(UUID uuid) {
        return profileLikedProduct.findByProfileId(uuid);
    }

    @Override
    public Mono<ProfileLikedProduct> addToFavorites(UUID userId, UUID productId) {
        log.info("{} - user id {}", getClass(), userId.toString());
        return save(new ProfileLikedProduct(userId, productId));
    }

    @Override
    public Mono<ProfileLikedProduct> save(ProfileLikedProduct u) {
        log.info("save user like - {}", u);
        return profileLikedProduct.save(u);
    }

    @Override
    public void remove(UUID userID, ProfileLikedProduct u) {
        log.info("remove user like - {} user id - {}", u, userID);
        profileLikedProduct.deleteByProfileIdAndProductId(userID, u);
    }

}
