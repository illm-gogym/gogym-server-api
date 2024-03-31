package com.gogym.apiserver.error.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    // Common
    NOT_FOUND(HttpStatus.NOT_FOUND, "NOT_FOUND", "NOT FOUND"),
    INTER_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR", "서버 내부 오류입니다."),

    // Gym
    NOT_FOUND_GYM(HttpStatus.NOT_FOUND, "NOT_FOUND_GYM", "존재하지 않는 사용자입니다."),

    // Trainer
    DUPLICATED_TRAINER_ID(HttpStatus.CONFLICT, "DUPLICATED_TRAINER_ID", "이미 사용중인 ID 입니다"),

    // User
    INVALID_PHONE_NUMBER(HttpStatus.BAD_REQUEST, "INVALID_PHONE_NUMBER", "전화번호 형식에 맞지 않습니다."),
    INVALID_DATE(HttpStatus.BAD_REQUEST, "INVALID_DATE", "날짜 데이터가 잘못 되었습니다."),
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "USER_NOT_FOUND", "사용자를 찾을 수 없습니다."),
    DUPLICATED_PHONE_NUMBER(HttpStatus.CONFLICT, "DUPLICATED_PHONE_NUMBER", "이미 사용중인 전화번호 입니다."),

    // Reservation
    NOT_FOUND_RESERVATION(HttpStatus.NOT_FOUND, "NOT_FOUND_RESERVATION", "예약정보를 찾을 수 없습니다."),

    // Registration
    NOT_FOUND_REGISTRATION(HttpStatus.NOT_FOUND, "NOT_FOUND_RESERVATION", "수강권 정보를 찾을 수 없습니다."),
    INVALID_REGISTRATION(HttpStatus.NOT_FOUND, "NOT_FOUND_RESERVATION", "수강권 정보가 올바르지 않습니다."),

    // Token
    INVALID_SIGNATURE(HttpStatus.UNAUTHORIZED, "INVALID_SIGNATURE", "잘못된 JWT 서명입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "EXPIRED_TOKEN", "만료된 JWT 토큰입니다."),
    NOT_SUPPORTED_TOKEN(HttpStatus.UNAUTHORIZED, "NOT_SUPPORTED_TOKEN", "지원되지 않는 JWT 토큰입니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "INVALID_TOKEN", "JWT 토큰이 잘못되었습니다."),

    // Login
    NOT_FOUND_USERPHONE(HttpStatus.NOT_FOUND, "NOTFOUND_USER", "없는 USER입니다."),
    NOT_FOUND_TRAINER(HttpStatus.NOT_FOUND, "NOTFOUND_TRAINER", "없는 Trainer 입니다."),
    WRONG_PASSWORD(HttpStatus.UNAUTHORIZED, "WRONG_PASSWORD", "비밀번호가 틀렸습니다.");



    private HttpStatus httpStatus;
    private String errorCode;
    private String message;

}
