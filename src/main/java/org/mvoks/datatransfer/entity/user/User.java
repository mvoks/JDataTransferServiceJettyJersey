package org.mvoks.datatransfer.entity.user;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder(builderClassName = "Builder")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {

    private Long id;
    private String username;
    private String password;
    private String passwordConfirmation;
    private Set<Role> roles;

    @ToString.Include(name = "id")
    private String toStringId() {
        return String.join(Long.toString(id), "'", "'");
    }

    @ToString.Include(name = "username")
    private String toStringUsername() {
        return String.join(username, "'", "'");
    }

    @ToString.Include(name = "password")
    private String toStringPassword() {
        return "'***'";
    }
}