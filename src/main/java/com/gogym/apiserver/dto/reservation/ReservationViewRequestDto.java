package com.gogym.apiserver.dto.reservation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ReservationViewRequestDto {

    @Nullable
    @ApiModelProperty(example = "010-1234-5678")
    private String userPhone;

    @ApiModelProperty(example = "2002-01-01 09:00", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Seoul")
    @Nullable
    private LocalDateTime startTime;

    @ApiModelProperty(example = "2002-01-01 10:00", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm", timezone = "Asia/Seoul")
    @Nullable
    private LocalDateTime endTime;
}
