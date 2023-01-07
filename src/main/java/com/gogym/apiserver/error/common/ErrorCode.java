package com.gogym.apiserver.error.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    // Common

    NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON-ERR-404", "PAGE NOT FOUND"),
    INTER_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON_SERVER_ERROR_001", "서버 내부 오류입니다."),
//    INTER_SERVER_ERROR(,HttpStatus.INTERNAL_SERVER_ERROR, "COMMON-ERR-500", "INTER SERVER ERROR"),
//    NOT_EXIST_USER(HttpStatus, "MEMBER-ERR-400", "NOT_EXIST_USER"),
//    PHONE_NUMBER_DUPLICATION(400, "MEMBER-ERR-400", "PHONE NUMBER DUPLICATED"),
//    TRAINER_ID_DUPLICATION(400, "MEMBER-ERR-400", "TRAINER ID DUPLICATED"),
//    TOKEN_EXPIRED(401, "TOKEN-ERR-401", "TOKEN IS EXPIRED"),
//    TOKEN_INVALID(401, "TOKEN-ERR-401", "INVALID TOKEN"),
//    TOKEN_UNSUPPORTED(401, "TOKEN-ERR-401", "UNSUPPORTED JWT TOKEN"),
//    TOKEN_ILLEGAL_ARGUMENT(401, "TOKEN-ERR-401", "ILLEGAL ARGUMENT TOKEN"),

    // Gym
    GYM_CONFLICT(HttpStatus.CONFLICT, "GYM_CONFLICT_001", "이미 사용중인 전화번호 입니다."),
    GYM_NOT_FOUND(HttpStatus.NOT_FOUND, "GYM_NOT_FOUND_001", "존재하지 않는 사용자입니다."),

    // Trainer
    TRAINER_ID_DUPLICATION(HttpStatus.CONFLICT, "TRAINER_CONFLICT_001", "이미 사용중인 전화번호 입니다"),

    // User
    NOT_EXIST_USER(HttpStatus.NOT_FOUND, "USER_NOT_FOUND", "사용자를 찾을 수 없습니다."),
    PHONE_NUMBER_DUPLICATION(HttpStatus.CONFLICT, "USER_CONFLICT_001", "이미 사용중인 전화번호 입니다."),

    // Token
    TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "TOKEN_UNAUTHORIZED_001", "잘못된 JWT 서명입니다."),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "TOKEN_UNAUTHORIZED_002", "만료된 JWT 토큰입니다."),
    TOKEN_UNSUPPORTED(HttpStatus.UNAUTHORIZED, "TOKEN_UNAUTHORIZED_002", "지원되지 않는 JWT 토큰입니다."),
    TOKEN_ILLEGAL_ARGUMENT(HttpStatus.UNAUTHORIZED, "TOKEN_UNAUTHORIZED_002", "JWT 토큰이 잘못되었습니다.");

    private HttpStatus httpStatus;
    private String errorCode;
    private String message;

    /*ErrorCode(HttpStatus httpStatus, String errorCode, String message) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.message = message;
    }*/

}
