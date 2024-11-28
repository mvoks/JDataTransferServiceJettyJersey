package org.mvoks.datatransfer.entity.user;

import java.io.Serializable;
import java.util.Set;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "users")
@Data
@NamedQuery(
    name = "user.findByUsername",
    query = "SELECT u FROM User u WHERE u.username = :username"
)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 64)
    private String username;
    @Column(nullable = false)
    private String password;
    @Transient
    private String passwordConfirmation;
    @Column(name = "role")
    @CollectionTable(name = "users_roles")
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(value = EnumType.STRING)
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