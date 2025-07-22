package org.faketri.entity.user.model;

import org.faketri.role.model.ERole;
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
    private String password;
    private List<ERole> roles;

    private List<UUID> likedProduct;

    public User() {
    }

    public User(UUID id, String email, String username, String password, List<UUID> likedProduct) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ERole> getRoles() {
        if(roles == null) roles = List.of(ERole.READ);
        return roles;
    }

    public void setRoles(List<ERole> roles) {
        this.roles = roles;
    }

    public List<UUID> getLikedProduct() {
        if (likedProduct == null) likedProduct = new ArrayList<>();
        return likedProduct;
    }

    public void setLikedProduct(List<UUID> likedProduct) {
        this.likedProduct = likedProduct;
    }

}
