package com.gogym.apiserver.dto.registration;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAllDto {
    private String name;
    private String user_phone;
    private String gender;
    private String ins_dtm;
    private String upd_dtm;
    private String start_date;
    private String end_date;
}
