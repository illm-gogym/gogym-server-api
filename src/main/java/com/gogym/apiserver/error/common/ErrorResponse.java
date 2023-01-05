package com.gogym.apiserver.error.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ErrorResponse {
    private HttpStatus status;
    private String code;
    private String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.status = errorCode.getHttpStatus();
        this.code = errorCode.getErrorCode();
        this.message = errorCode.getMessage();
    }

    public ErrorResponse(ErrorCode errorCode, String addMessage) {
        this.status = errorCode.getHttpStatus();
        this.code = errorCode.getErrorCode();
        this.message = errorCode.getMessage() + " " + addMessage;
    }

    public ErrorResponse(HttpStatus httpStatus, String code, String message) {
        this.status = httpStatus;
        this.code = code;
        this.message = message;
    }
}
