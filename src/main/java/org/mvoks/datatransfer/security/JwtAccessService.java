package org.mvoks.datatransfer.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import jakarta.inject.Inject;
import org.jvnet.hk2.annotations.Service;

@Service
public class JwtAccessService extends JwtAbstractService implements JwtService {

    private final JwtHelper jwtHelper;

    @Inject
    public JwtAccessService(JwtHelper jwtHelper) {
        super(jwtHelper.getSecretKey(), "ACCESS_TOKEN");
        this.jwtHelper = jwtHelper;
    }

    @Override
    public String createToken(Long userId, String username, Map<? extends String, ?> parameters) {
        return createToken(
            userId,
            username,
            Instant.now().plus(jwtHelper.getJwtProperties().getAccessInMinutes(), ChronoUnit.MINUTES),
            parameters
        );
    }
}