package com.gogym.apiserver.dto.registration.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.gogym.apiserver.dto.user.wrapper.UserReservationWrapper;
import com.gogym.apiserver.entity.Registration;
import com.gogym.apiserver.entity.Reservation;
import com.gogym.apiserver.entity.User;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public interface RegistrationWrapper {
    public Registration getRegistration();

    public User getUser();
}
