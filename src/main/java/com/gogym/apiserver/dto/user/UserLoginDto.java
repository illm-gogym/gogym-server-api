package com.gogym.apiserver.dto.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserLoginDto {

    @NotNull
    private String userPhone;

    @NotNull
    @Size(min = 3, max = 100)
    private String password;
}