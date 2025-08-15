package org.example.infrastructure.basket.dto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public class BasketDtoRequest {
    
    private UUID id;
    private UUID userId;
    private Map<UUID, Integer> productId;
    private BigDecimal price;

    public BasketDtoRequest() {
    }

    public BasketDtoRequest(UUID id, UUID userId, Map<UUID, Integer> productId, BigDecimal price) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.price = price;
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
    public BigDecimal getPrice() {
        return price;
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

    public void setPrice(BigDecimal price) {
        if (price == null) 
            throw new IllegalArgumentException("Price cannot be null");
        
        if (price.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Price cannot be negative");
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BasketDtoRequest)) return false;

        BasketDtoRequest other = (BasketDtoRequest) obj;
        return id.equals(other.id) &&
               userId.equals(other.userId) &&
               productId.equals(other.productId) &&
               price.equals(other.price);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + userId.hashCode();
        result = 31 * result + productId.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BasketDtoRequest{" +
               "id=" + id +
               ", userId=" + userId +
               ", productId=" + productId +
               ", price=" + price +
               '}';
    }
}

