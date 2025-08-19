package org.catalog.entity.product.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Document(indexName = "products")
public class Product {
    @Id
    private String id;
    @Field(type = FieldType.Text)
    private String chapterId;
    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Text)
    private String description;
    @Field(type = FieldType.Keyword)
    private List<String> images;
    @Field(type = FieldType.Scaled_Float, scalingFactor = 100)
    private BigDecimal price;

    public Product() {
    }

    public Product(String id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void setPrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) > 0)
            this.price = price;
        else
            throw new RuntimeException("The price for product cannot be zero or less");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Product)) return false;
        Product other = (Product) obj;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chapterId, name, description, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", chapterId=" + chapterId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
