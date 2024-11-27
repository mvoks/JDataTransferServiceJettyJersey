package org.mvoks.datatransfer.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class UserRegistrationDto extends UserPasswordDto {
    private String username;
}