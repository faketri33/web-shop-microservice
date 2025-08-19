package org.faketri.entity.profile.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;
import java.util.UUID;

@Table
public class ProfileLikedProduct {

    @Id
    private UUID id;
    private UUID profileId;
    private UUID productId;

    public ProfileLikedProduct() {
    }

    public ProfileLikedProduct(UUID id, UUID profileId, UUID productId) {
        this.id = id;
        this.profileId = profileId;
        this.productId = productId;
    }

    public ProfileLikedProduct(UUID profileId, UUID productId) {
        this.profileId = profileId;
        this.productId = productId;
    }

    public UUID getProfileId() {
        return profileId;
    }

    public void setProfileId(UUID profileId) {
        this.profileId = profileId;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProfileLikedProduct that = (ProfileLikedProduct) o;
        return Objects.equals(id, that.id) && Objects.equals(profileId, that.profileId) && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, profileId, productId);
    }

    @Override
    public String toString() {
        return "ProfileLikedProduct{" +
                "userId=" + profileId +
                ", productId=" + productId +
                '}';
    }
}
