package org.auth.usecase.auth.model;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.auth.infrastructure.auth.model.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class JwtUtilsImpl implements JwtUtils {

    private static final Logger log = Logger.getLogger(JwtUtils.class.getName());

    private final Key secret;

    public JwtUtilsImpl(@Value("${jwt.secret}") String secret) {
        this.secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    @Override
    public String createToken(String username, String role) {
        final LocalDateTime now = LocalDateTime.now();
        final Instant accessExpirationInstant = now.plusMinutes(30).atZone(ZoneId.systemDefault()).toInstant();
        final Date accessExpiration = Date.from(accessExpirationInstant);

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);

        log.info("Jwt Utils secure = " + secret);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setExpiration(accessExpiration)
                .signWith(secret)
                .compact();
    }
}
