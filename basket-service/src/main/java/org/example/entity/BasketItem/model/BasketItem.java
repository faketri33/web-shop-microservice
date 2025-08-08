package org.example.entity.BasketItem.model;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
public class BasketItem {

    @Id
    private UUID id;
    private UUID basketId;
    private UUID productId;
    private int quantity;
    private BigDecimal price;


    public BasketItem(UUID id2, UUID productId2, Integer quantity2, BigDecimal price2) {
        //TODO Auto-generated constructor stub
    }

    public UUID getId() {
        return id;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
                id.equals(that.id) &&
                basketId.equals(that.basketId) &&
                productId.equals(that.productId) &&
                price.equals(that.price);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + basketId.hashCode();
        result = 31 * result + productId.hashCode();
        result = 31 * result + quantity;
        result = 31 * result + price.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BasketItem{" +
               "id=" + id +
               ", basketId=" + basketId +
               ", productId=" + productId +
               ", quantity=" + quantity +
               ", price=" + price +
               '}';
    }
}
