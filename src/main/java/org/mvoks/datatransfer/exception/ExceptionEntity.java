package org.mvoks.datatransfer.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Builder(builderClassName = "Builder", access = AccessLevel.PACKAGE)
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
class ExceptionEntity {
    private final String message;
    private final String details;
}