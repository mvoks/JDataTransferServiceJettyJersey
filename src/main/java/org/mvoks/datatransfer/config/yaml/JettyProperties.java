package org.mvoks.datatransfer.config.yaml;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JettyProperties {
    private String host;
    private int port;
}