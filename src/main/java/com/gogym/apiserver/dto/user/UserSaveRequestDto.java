package com.gogym.apiserver.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.gogym.apiserver.entity.common.Role;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;


@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserSaveRequestDto {

    @Id
    @ApiModelProperty(example = "01012345678", required = true)
    private String phone;

    @ApiModelProperty(value = "password", dataType = "string", required = true, example = "test1234")
    private String password;
    @ApiModelProperty(example = "홍길동", required = true)
    private String name;
    @ApiModelProperty(example = "Man", required = true)
    private String gender;
    @ApiModelProperty(example = "2002-01-01")
    private String birth;

    @ApiModelProperty(example = "1001")
    private Long gymId;

    @ApiModelProperty(example = "bellgym")
    private String trainerId;

    @ApiModelProperty(example = "30")
    private Integer total;

    @ApiModelProperty(example = "0")
    private Integer remaining;

    @ApiModelProperty(example = "2022-12-31")
    private String until;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty(example = "ROLE_USER")
    private Role role;
}
