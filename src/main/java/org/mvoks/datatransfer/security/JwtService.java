package org.mvoks.datatransfer.security;

import java.util.Map;
import org.jvnet.hk2.annotations.Contract;

@Contract
public interface JwtService {

    boolean validateToken(String token);

    Long getUserId(String token);

    String getUsername(String token);

    String createToken(Long userId, String username, Map<? extends String, ?> parameters);
}