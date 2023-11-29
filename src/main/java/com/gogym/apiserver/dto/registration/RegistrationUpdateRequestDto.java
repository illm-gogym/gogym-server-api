package com.gogym.apiserver.dto.registration;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
public class RegistrationUpdateRequestDto {
    private Long registrationId;
    private Long gymId;
    private String trainerId;
    private String userPhone;
    private int total;
    private int remaining;
    private int status;
    private Date date;
}
