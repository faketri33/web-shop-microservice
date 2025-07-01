package org.faketri.infastructure.secure.config;

public interface JwtUtils {

    public String extractUserName(String token);
    public Boolean validToken(String token);
}
