package org.faketri.entity.user.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    private String email;
    private String username;
    private String password;

    private List<UUID> productUUID;

    public User() {
    }

    public User(UUID id, String email, String username, String password, List<UUID> productUUID) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.productUUID = productUUID;
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

    public List<UUID> getProductUUID() {
        if (productUUID == null) productUUID = new ArrayList<>();
        return productUUID;
    }

    public void setProductUUID(List<UUID> productUUID) {
        this.productUUID = productUUID;
    }

}
