package org.mvoks.datatransfer.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mvoks.datatransfer.validation.ActionCreate;

@Getter
@Setter
@ToString(callSuper = true)
public class UserRegistrationDto extends UserPasswordDto {
    @NotNull(
        message = "Username mustn't be 'null'.",
        groups = ActionCreate.class
    )
    @Size(
        min = 1,
        max = 65,
        message = "Username must be specified and less then 65 symbols.",
        groups = ActionCreate.class
    )
    @Email(
        message = "Username must be a valid email address.",
        groups = ActionCreate.class
    )
    private String username;
}