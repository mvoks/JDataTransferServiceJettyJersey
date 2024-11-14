package org.mvoks.datatransfer.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import jakarta.inject.Inject;
import org.jvnet.hk2.annotations.Service;

@Service
public class JwtRefreshService extends JwtAbstractService implements JwtService {

    private final JwtHelper jwtHelper;

    @Inject
    public JwtRefreshService(JwtHelper jwtHelper) {
        super(jwtHelper.getSecretKey(), "REFRESH_TOKEN");
        this.jwtHelper = jwtHelper;
    }

    @Override
    public String createToken(Long userId, String username, Map<? extends String, ?> parameters) {
        return createToken(
            userId,
            username,
            Instant.now().plus(jwtHelper.getJwtProperties().getRefreshInDays(), ChronoUnit.DAYS),
            parameters
        );
    }
}