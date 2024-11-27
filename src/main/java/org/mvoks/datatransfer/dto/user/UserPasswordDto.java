package org.mvoks.datatransfer.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
class UserPasswordDto {

    private String password;
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