package org.mvoks.datatransfer.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRefresh {
    private String token;
}