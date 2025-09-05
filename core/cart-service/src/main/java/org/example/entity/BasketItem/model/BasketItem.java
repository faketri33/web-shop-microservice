package org.example.entity.BasketItem.model;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
public class BasketItem {

    private UUID basketId;
    private UUID productId;
    private int quantity;


    public BasketItem(UUID basketId, UUID productId, int quantity) {
        this.basketId = basketId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public UUID getBasketId() {
        return basketId;
    }

    public UUID getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setBasketId(UUID basketId) {
        this.basketId = basketId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof BasketItem)) return false;
        BasketItem that = (BasketItem) obj;
        return quantity == that.quantity &&
                basketId.equals(that.basketId) &&
                productId.equals(that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basketId, productId, quantity);
    }

    @Override
    public String toString() {
        return "BasketItem{" +
               ", basketId=" + basketId +
               ", productId=" + productId +
               ", quantity=" + quantity +
               '}';
    }
}
