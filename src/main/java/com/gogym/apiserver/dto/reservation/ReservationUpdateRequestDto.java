package com.gogym.apiserver.dto.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReservationUpdateRequestDto {
    @ApiModelProperty(example = "1", required = true)
    private Long registrationId;

    @ApiModelProperty(example = "010-1234-5678", required = true)
    private String userPhone;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Seoul")
    @ApiModelProperty(example = "2002-01-01 09:00", required = true)
    private LocalDateTime startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Seoul")
    @ApiModelProperty(example = "2002-01-01 10:00", required = true)
    private LocalDateTime endTime;

    @ApiModelProperty(example = "squat 100KG 5*5", required = true)
    private String description;

    @ApiModelProperty(example = "-1")
    private Integer usageState;

}
