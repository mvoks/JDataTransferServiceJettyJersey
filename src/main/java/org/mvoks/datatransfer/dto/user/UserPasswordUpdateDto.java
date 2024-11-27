package org.mvoks.datatransfer.dto.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class UserPasswordUpdateDto extends UserPasswordDto {
    private Long id;
    private String username;
}