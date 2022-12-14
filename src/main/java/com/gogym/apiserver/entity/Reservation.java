package com.gogym.apiserver.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.gogym.apiserver.entity.common.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Reservation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservationId;

    private Long registrationId;
    private String trainerId;
    private String userPhone;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
    private int usageState;

    public void updateReservation(String userPhone, LocalDateTime startTime, LocalDateTime endTime, String description, Integer usageState) {
        this.userPhone = userPhone;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.usageState = usageState;
    }
}
