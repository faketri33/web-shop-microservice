package org.faketri.infastructure.user.dto.response;

import java.util.UUID;

public class UserResponseDTO {

    private UUID id;
    private String email;
    private String username;

    public UserResponseDTO() {
    }

    public UserResponseDTO(UUID id, String email, String username) {
        this.id = id;
        this.email = email;
        this.username = username;
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
}
