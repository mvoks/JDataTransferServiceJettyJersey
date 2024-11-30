package org.mvoks.datatransfer.dto.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mvoks.datatransfer.validation.ActionCreate;
import org.mvoks.datatransfer.validation.ActionUpdate;

@Getter
@Setter
@ToString
class UserPasswordDto {

    @NotNull(
        message = "Password mustn't be 'null'.",
        groups = {ActionCreate.class, ActionUpdate.class}
    )
    @Size(
        min = 1,
        max = 65,
        message = "Password must be specified and less then 65 symbols.",
        groups = {ActionCreate.class, ActionUpdate.class}
    )
    private String password;
    @NotNull(
        message = "Password confirmation mustn't be 'null'.",
        groups = {ActionCreate.class, ActionUpdate.class}
    )
    @Size(
        min = 1,
        max = 65,
        message = "Password confirmation must be specified and less then 65 symbols.",
        groups = {ActionCreate.class, ActionUpdate.class}
    )
    private String passwordConfirmation;

    @ToString.Include(name = "password")
    private String toStringPassword() {
        return "***";
    }

    @ToString.Include(name = "passwordConfirmation")
    private String toStringPasswordConfirmation() {
        return "***";
    }
}