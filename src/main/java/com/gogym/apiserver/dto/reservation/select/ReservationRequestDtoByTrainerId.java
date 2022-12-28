package com.gogym.apiserver.dto.reservation.select;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.List;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReservationRequestDtoByTrainerId {
    @ApiModelProperty(example = "bellgym")
    private List<String> trainerId;
}
