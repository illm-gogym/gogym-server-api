package com.gogym.apiserver.dto.registration;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RegistrationRequestDtoByUserPhone {
    @ApiModelProperty(example = "01012345678")
    private String userPhone;
}
