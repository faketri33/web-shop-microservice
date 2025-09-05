package org.example.infrastructure.basket.dto;

import java.util.Map;
import java.util.UUID;

public class BasketDto {
    
    private UUID id;
    private UUID userId;
    private Map<UUID, Integer> productId;

    public BasketDto() {
    }

    public BasketDto(UUID id, UUID userId, Map<UUID, Integer> productId) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }
    public Map<UUID, Integer> getProductId() {
        return productId;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    
    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setProductId(Map<UUID, Integer> productId) {
        if (productId == null || productId.isEmpty()) 
            throw new IllegalArgumentException("Product ID map cannot be null or empty");
        
        this.productId = productId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BasketDtoRequest)) return false;

        BasketDtoRequest other = (BasketDtoRequest) obj;
        return id.equals(other.id) &&
               userId.equals(other.userId) &&
               productId.equals(other.productId);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + userId.hashCode();
        result = 31 * result + productId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BasketDtoRequest{" +
               "id=" + id +
               ", userId=" + userId +
               ", productId=" + productId +
               '}';
    }
}

