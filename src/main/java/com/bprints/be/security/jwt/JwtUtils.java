package com.bprints.be.security.jwt;

import com.bprints.be.security.service.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtUtils {
    @Value("${bprints.app.jwt.secret.path}")
    private String jwtSecretKeyPath;

    @Value("${bprints.app.jwt.expiration}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        Map<String, String> claims = setClaimsForToken(userPrincipal);

        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .setClaims(claims)
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Map<String, String> setClaimsForToken(UserDetailsImpl userPrincipal) {
        Map<String, String> claims = new HashMap<>();
        claims.put("USER_NAME", userPrincipal.getUsername());
        claims.put("ROLE", userPrincipal.getAuthorities().toArray()[0].toString());
        return claims;
    }

    @SneakyThrows
    private Key key() {
        byte[] keyBytes = Files.readAllBytes(Paths.get(jwtSecretKeyPath));
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getRoleFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().get("ROLE").toString();
    }

    public String getUsernameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().get("USER_NAME").toString();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }

        return false;
    }
}
