package com.gogym.apiserver.dto.trainer;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TrainerLoginDto {

    @NotNull
    private String trainerId;

    @NotNull
    @Size(min = 3, max = 100)
    private String password;
}