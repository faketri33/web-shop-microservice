package org.faketri.infrastructure.pojo.mapper;

import org.faketri.entity.profile.model.Profile;
import org.faketri.infrastructure.pojo.response.ProfileResponsePojo;
import reactor.core.publisher.Mono;

public class ProfileMapper {
    private ProfileMapper() {
    }

    public static Mono<ProfileResponsePojo> toPojo(Profile profile) {
        return Mono.just(new ProfileResponsePojo(profile.getId(), profile.getEmail(), profile.getUsername(), profile.getImages()));
    }
}
