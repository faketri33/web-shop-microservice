package org.product.entity.productImage.model;

import java.util.Objects;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
public class ProductImage {


    @Id
    private UUID id;
    private UUID productId;
    private String imageUrl;

    public UUID getId() {
        return id;
    }

    public UUID getProductId() {
        return productId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ProductImage)) return false;
        ProductImage other = (ProductImage) obj;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productId, imageUrl);
    }

    @Override
    public String toString() {
        return "ProductImage{" +
                "id=" + id +
                ", productId=" + productId +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

}
