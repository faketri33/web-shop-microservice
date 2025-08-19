package org.faketri.entity.profile.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Table
public class Profile {

    @Id
    private UUID id;

    private String email;
    private String username;
    private String images;
    private Instant createAt = Instant.now();

    public Profile() {
    }

    public Profile(UUID id, String email, String username, String images) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.images = images;
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

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Instant getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Instant createAt) {
        this.createAt = createAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(id, profile.id) && Objects.equals(email, profile.email) && Objects.equals(username, profile.username) && Objects.equals(images, profile.images);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, username, images);
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}