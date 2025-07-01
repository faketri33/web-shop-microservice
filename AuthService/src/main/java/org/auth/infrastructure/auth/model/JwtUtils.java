package org.auth.infrastructure.auth.model;

public interface JwtUtils {

    public String createToken(String username, String role);
}
