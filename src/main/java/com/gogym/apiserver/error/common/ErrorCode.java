package com.gogym.apiserver.error.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    NOT_FOUND(404,"COMMON-ERR-404","PAGE NOT FOUND"),
    INTER_SERVER_ERROR(500,"COMMON-ERR-500","INTER SERVER ERROR"),
    PHONE_NUMBER_DUPLICATION(400,"MEMBER-ERR-400","PHONE NUMBER DUPLICATED"),
    TRAINER_ID_DUPLICATION(400,"MEMBER-ERR-400","TRAINER ID DUPLICATED"),
    TOKEN_EXPIRED(401, "TOKEN-ERR-401", "TOKEN IS EXPIRED"),
    TOKEN_INVALID(401, "TOKEN-ERR-401", "INVALID TOKEN"),
    TOKEN_UNSUPPORTED(401, "TOKEN-ERR-401", "UNSUPPORTED JWT TOKEN"),
    TOKEN_ILLEGAL_ARGUMENT(401, "TOKEN-ERR-401", "ILLEGAL ARGUMENT TOKEN"),
    ;

    private int status;
    private String errorCode;
    private String message;
}
