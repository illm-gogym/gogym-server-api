package com.gogym.apiserver.error.handler;

import com.gogym.apiserver.error.common.ErrorCode;
import com.gogym.apiserver.error.common.ErrorResponse;
import com.gogym.apiserver.error.exception.PhoneNumberDuplicateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PhoneNumberDuplicateException.class)
    public ResponseEntity<ErrorResponse> handleEmailDuplicateException(PhoneNumberDuplicateException ex){
        System.out.println("handlePhoneNumberDuplicateException : " + ex);
        ErrorResponse response = new ErrorResponse(ex.getErrorCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex){
        System.out.println("handleException : " + ex);
        ErrorResponse response = new ErrorResponse(ErrorCode.INTER_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
