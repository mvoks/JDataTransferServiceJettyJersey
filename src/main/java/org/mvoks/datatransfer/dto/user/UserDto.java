package org.mvoks.datatransfer.dto.user;

import java.util.Set;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mvoks.datatransfer.entity.user.Role;
import org.mvoks.datatransfer.validation.ActionUpdate;

@Getter
@Setter
@ToString
public class UserDto {
    @NotNull(
        message = "Id mustn't be 'null'.",
        groups = ActionUpdate.class
    )
    @Min(
        message = "Id must be more then 0.",
        value = 1,
        groups = ActionUpdate.class
    )
    private Long id;
    @NotNull(
        message = "Username mustn't be 'null'.",
        groups = ActionUpdate.class
    )
    @Size(
        min = 1,
        max = 65,
        message = "Username must be specified and less then 65 symbols.",
        groups = ActionUpdate.class
    )
    @Email(
        message = "Username must be a valid email address.",
        groups = ActionUpdate.class
    )
    private String username;
    private Set<Role> roles;
}