package org.mvoks.datatransfer.dto.auth;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRefresh {
    @NotNull(message = "Refresh token mustn't be 'null'.")
    @Size(
        min = 1,
        max = 512,
        message = "Refresh token be less then 512 symbols."
    )
    private String token;
}