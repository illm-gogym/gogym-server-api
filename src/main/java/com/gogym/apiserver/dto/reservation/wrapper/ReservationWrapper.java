package com.gogym.apiserver.dto.reservation.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.gogym.apiserver.dto.user.UserReservationWrapper;
import com.gogym.apiserver.entity.Reservation;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public interface ReservationWrapper extends UserReservationWrapper {
    public Reservation getReservation();
    public UserReservationWrapper getUser();
}
