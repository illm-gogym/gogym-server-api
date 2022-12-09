package com.gogym.apiserver.error.exception;

import com.gogym.apiserver.error.common.ErrorCode;
import lombok.Getter;

@Getter
public class NotExistUserException extends RuntimeException {

    private ErrorCode errorCode;

    public NotExistUserException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
