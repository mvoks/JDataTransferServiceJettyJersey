package org.mvoks.datatransfer.security;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import org.mvoks.datatransfer.config.yaml.YamlService;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class JwtService {

    private final YamlService yamlService;
    private JwtProperties jwtProperties;
    private SecretKey secretKey;

    @PostConstruct
    void init() {
        jwtProperties = yamlService.getYamlProperties()
            .getSecurity()
            .getJwt();
        this.secretKey = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
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
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    public String createAccessToken(Long userId, String username, Set<? extends Enum<?>> roles) {
        final Set<String> setRoles = roles.stream()
            .map(Enum::name)
            .collect(Collectors.toSet());
        final Claims claims = Jwts.claims()
            .subject(username)
            .add("id", userId)
            .add("roles", setRoles)
            .build();
        return createToken(
            claims,
            Instant.now().plus(jwtProperties.getAccessInMinutes(), ChronoUnit.MINUTES)
        );
    }

    public String createRefreshToken(Long userId, String username) {
        final Claims claims = Jwts.claims()
            .subject(username)
            .add("id", userId)
            .build();
        return createToken(
            claims,
            Instant.now().plus(jwtProperties.getRefreshInDays(), ChronoUnit.DAYS)
        );
    }

    private String createToken(Claims claims, Instant expiration) {
        return Jwts.builder()
            .claims(claims)
            .expiration(Date.from(expiration))
            .signWith(secretKey)
            .compact();
    }
}