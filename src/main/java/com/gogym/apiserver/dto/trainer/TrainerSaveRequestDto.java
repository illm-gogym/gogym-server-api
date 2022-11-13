package com.gogym.apiserver.dto.trainer;

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
public class TrainerSaveRequestDto {

    @Id
    @ApiModelProperty(example = "bellgym", required = true)
    private String trainerId;

    @ApiModelProperty(value = "password", dataType = "string", required = true, example = "test1234")
    private String password;
    @ApiModelProperty(example = "홍길동", required = true)
    private String name;
    @ApiModelProperty(example = "01012345678", required = true)
    private String phone;
    @ApiModelProperty(example = "1001", required = true)
    private Long gymId;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty(example = "ROLE_ADMIN")
    private Role role;
}
