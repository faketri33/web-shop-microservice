package org.product.entity.product.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Table
public class Product {
    @Id
    private UUID id;
    private UUID chapterId;

    private String name;
    private String description;
    private List<String> images;

    private BigDecimal price;

    public Product() {
    }

    public Product(UUID id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getChapterId() {
        return chapterId;
    }

    public void setChapterId(UUID chapterId) {
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

    public void setPrice(BigDecimal price) {
        if (price.compareTo(BigDecimal.ZERO) > 0)
            this.price = price;
        else
            throw new RuntimeException("The price for product cannot be zero or less");
    }
}
