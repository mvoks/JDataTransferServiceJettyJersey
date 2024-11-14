package org.mvoks.datatransfer.security;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JwtProperties {

    private String secret;
    private int accessInMinutes;
    private int refreshInDays;

    @ToString.Include(name = "secret")
    private String toStringSecret() {
        return "***";
    }
}