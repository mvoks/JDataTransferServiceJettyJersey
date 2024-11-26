package org.mvoks.datatransfer.dto;

import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mvoks.datatransfer.entity.user.Role;

@Getter
@Setter
@ToString
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private Set<Role> roles;
}