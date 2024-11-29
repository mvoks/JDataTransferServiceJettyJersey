package org.mvoks.datatransfer.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequest {
    @NotNull(message = "Username mustn't be 'null'.")
    @Size(
        min = 1,
        max = 65,
        message = "Username must be less then 65 symbols."
    )
    @Email(message = "Username must be a valid email address.")
    private String username;
    @NotNull(message = "Password mustn't be 'null'.")
    @Size(
        min = 1,
        max = 65,
        message = "Password must be less then 65 symbols."
    )
    private String password;
}