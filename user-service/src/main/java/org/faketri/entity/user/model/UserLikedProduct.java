package org.faketri.entity.user.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;
import java.util.UUID;

@Table
public class UserLikedProduct {

    @Id
    private UUID id;
    private UUID userId;
    private UUID productId;

    public UserLikedProduct() {
    }

    public UserLikedProduct(UUID id, UUID userId, UUID productId) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
    }

    public UserLikedProduct(UUID userId, UUID productId) {
        this.userId = userId;
        this.productId = productId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
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
        UserLikedProduct that = (UserLikedProduct) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, productId);
    }

    @Override
    public String toString() {
        return "UserLikedProduct{" +
                "userId=" + userId +
                ", productId=" + productId +
                '}';
    }
}
