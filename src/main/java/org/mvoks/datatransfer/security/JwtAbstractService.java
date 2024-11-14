package org.mvoks.datatransfer.security;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ClaimsBuilder;
import io.jsonwebtoken.Jwts;

abstract class JwtAbstractService {

    private final SecretKey secretKey;
    private final String issuer;

    JwtAbstractService(SecretKey secretKey, String issuer) {
        this.secretKey = secretKey;
        this.issuer = issuer;
    }

    public boolean validateToken(String token) {
        return getPayload(token)
            .getExpiration()
            .after(new Date());
    }

    public Long getUserId(String token) {
        return getPayload(token).get("id", Long.class);
    }

    public String getUsername(String token) {
        return getPayload(token).getSubject();
    }

    private Claims getPayload(String token) {
        return Jwts.parser()
            .verifyWith(secretKey)
            .requireIssuer(issuer)
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    String createToken(Long userId,
                       String username,
                       Instant expiration,
                       Map<? extends String, ?> parameters) {
        final ClaimsBuilder claimsBuilder = Jwts.claims()
            .subject(username)
            .add("id", userId);
        if (parameters != null && !parameters.isEmpty()) {
            claimsBuilder.add(parameters);
        }
        return Jwts.builder()
            .claims(claimsBuilder.build())
            .expiration(Date.from(expiration))
            .signWith(secretKey)
            .issuer(issuer)
            .compact();
    }
}