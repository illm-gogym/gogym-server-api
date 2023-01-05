package com.gogym.apiserver.error.exception;

import com.gogym.apiserver.error.common.ErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = false)
public class CommonException extends RuntimeException {

    private final String message;
    private final String code;
    private final HttpStatus httpStatus;

    public CommonException(String message, String errorCode, HttpStatus httpStatus) {
        this.message = message;
        this.code = errorCode;
        this.httpStatus = httpStatus;
    }

    public CommonException(ErrorCode commonErrorMessage) {
        this.httpStatus = commonErrorMessage.getHttpStatus();
        this.code = commonErrorMessage.getErrorCode();
        this.message = commonErrorMessage.getMessage();
    }

    public CommonException(CommonException commonErrorMessage, String addMessage) {
        this.httpStatus = commonErrorMessage.getHttpStatus();
        this.code = commonErrorMessage.getCode();
        this.message = commonErrorMessage.getMessage() + " " + addMessage;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
