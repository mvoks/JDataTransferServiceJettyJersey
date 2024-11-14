package org.mvoks.datatransfer.security;

import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import org.mvoks.datatransfer.config.yaml.YamlService;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Inject))
@Getter
public class JwtHelper {

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
}