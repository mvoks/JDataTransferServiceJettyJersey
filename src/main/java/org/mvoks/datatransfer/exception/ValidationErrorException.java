package org.mvoks.datatransfer.exception;

import lombok.Getter;

@Getter
public class ValidationErrorException extends RuntimeException {

    private String detail = null;

    public ValidationErrorException(String message) {
        super(message);
    }

    public ValidationErrorException(String message, String detail) {
        super(message);
        this.detail = detail;
    }
}