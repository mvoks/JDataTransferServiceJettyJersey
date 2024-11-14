package org.mvoks.datatransfer.config.yaml;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mvoks.datatransfer.security.JwtProperties;

@Getter
@Setter
@ToString
public class SecurityProperties {
    private JwtProperties jwt;
}