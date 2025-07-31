package org.faketri.entity.user.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table
public class User {

    @Id
    private UUID id;

    private String email;
    private String username;
    private String images;
    private List<UUID> likedProduct;

    public User() {
    }

    public User(UUID id, String email, String username, String images, List<UUID> likedProduct) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.images = images;
        this.likedProduct = likedProduct;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<UUID> getLikedProduct() {
        if (likedProduct == null) likedProduct = new ArrayList<>();
        return likedProduct;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public void setLikedProduct(List<UUID> likedProduct) {
        this.likedProduct = likedProduct;
    }

}
