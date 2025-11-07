package com.indocyber.security.auth.jwt;

import com.indocyber.security.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration}") // Default: 24 hours in milliseconds
    private Long jwtExpiration;

    public String generateToken(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("role", user.getRole().name());
        // Remove or customize this based on your needs
        // extraClaims.put("fullName", user.getFirstName() + " " + user.getLastName());

        return generateToken(user, extraClaims);
    }

    private String generateToken(User user, Map<String, Object> extraClaims) {
        long now = System.currentTimeMillis();

        return Jwts.builder()
                .subject(user.getUsername())
                .issuer("security-example") // Change to your app name
                .issuedAt(new Date(now))
                .expiration(new Date(now + jwtExpiration))
                .claims(extraClaims)
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Boolean isValid(String token, String username) {
        return isUsernameValid(username, token) && !isTokenExpired(token);
    }

    private Boolean isUsernameValid(String username, String token) {
        return getClaims(token).getSubject().equals(username);
    }

    private Boolean isTokenExpired(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    public Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(String token) {
        return getClaims(token).getSubject();
    }

    public Date extractExpiration(String token) {
        return getClaims(token).getExpiration();
    }
}