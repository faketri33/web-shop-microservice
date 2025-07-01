package org.faketri.usecase.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.faketri.infastructure.secure.config.JwtUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.logging.Logger;

@Component
public class JwtUtilsImpl implements JwtUtils {

    private static final Logger log = Logger.getLogger(JwtUtilsImpl.class.getName());

    private final Key secret;

    public JwtUtilsImpl(@Value("${jwt.secret}") String secret) {
        this.secret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    @Override
    public String extractUserName(String token) {
        return getClaims(token, secret).getSubject();
    }

    @Override
    public Boolean validToken(String token) {
        return validateToken(token, secret);
    }

    private boolean validateToken(String token, Key secret) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            log.warning("Token expired");
        } catch (UnsupportedJwtException unsEx) {
            log.warning("Unsupported jwt " + unsEx.getMessage());
        } catch (MalformedJwtException mjEx) {
            log.warning("Malformed jwt " + mjEx.getMessage());
        } catch (SignatureException sEx) {
            log.warning("Invalid signature " + sEx.getMessage());
        } catch (Exception e) {
            log.warning("invalid token " + e.getMessage());
        }
        return false;
    }

    private Claims getClaims(String token, Key secret) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
