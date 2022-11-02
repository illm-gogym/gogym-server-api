package com.gogym.apiserver.error.exception;

import com.gogym.apiserver.error.common.ErrorCode;
import lombok.Getter;

@Getter
public class PhoneNumberDuplicateException extends RuntimeException {

    private ErrorCode errorCode;

    public PhoneNumberDuplicateException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
