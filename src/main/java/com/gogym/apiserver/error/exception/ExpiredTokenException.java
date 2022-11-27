package com.gogym.apiserver.error.exception;

import com.gogym.apiserver.error.common.ErrorCode;
import io.jsonwebtoken.JwtException;
import lombok.Getter;

@Getter
public class ExpiredTokenException extends JwtException {
    private ErrorCode errorCode;

    public ExpiredTokenException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
