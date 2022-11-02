package com.gogym.apiserver.dto.user;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class UserLoginDto {

    @NotNull
    private String userPhone;

    @NotNull
    @Size(min = 3, max = 100)
    private String password;
}