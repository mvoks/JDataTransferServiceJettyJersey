package org.mvoks.datatransfer.exception;

import lombok.Getter;

@Getter
public class AuthenticationException extends RuntimeException {

    private String detail = null;

    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(String message, String detail) {
        super(message);
        this.detail = detail;
    }
}