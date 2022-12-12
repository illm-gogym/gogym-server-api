package com.gogym.apiserver.error.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
    private int status;
    private String message;
    private String code;

    public ErrorResponse(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
        this.code = errorCode.getErrorCode();
    }

    public ErrorResponse(ErrorCode errorCode, String addMessage) {
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage() + " " + addMessage;
        this.code = errorCode.getErrorCode();
    }
}
