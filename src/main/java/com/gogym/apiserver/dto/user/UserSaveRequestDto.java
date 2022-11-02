package com.gogym.apiserver.dto.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.gogym.apiserver.entity.common.Role;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;


@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserSaveRequestDto {

    @Id
    private String phone;

    private String password;
    private String name;
    private String gender;
    private String birth;

    @Enumerated(EnumType.STRING)
    private Role role;
}
