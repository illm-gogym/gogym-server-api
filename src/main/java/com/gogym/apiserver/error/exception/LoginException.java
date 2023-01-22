package com.gogym.apiserver.error.exception;

import com.gogym.apiserver.error.common.ErrorCode;
import lombok.Getter;
import org.springframework.security.authentication.BadCredentialsException;

@Getter
public class LoginException extends BadCredentialsException {

    private ErrorCode errorCode;

    public LoginException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
