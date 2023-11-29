package com.gogym.apiserver.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.gogym.apiserver.entity.common.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Registration extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registrationId;

    @Column(name = "gym_id")
    private Long gymId;

    @Column(name = "trainer_id")
    private String trainerId;

    @Column(name = "user_phone")
    private String userPhone;

    @Column(name = "total")
    private int total;

    @Column(name = "remaining")
    private int remaining;

    private int status;

    @Column(name = "date_of_use")
    @Temporal(TemporalType.DATE)
    private Date date;

    public void update() {

    }

    public void updateTotal(int total) {
        this.total = total;
    }

    public void updateRemaining(int remaining) {
        this.remaining = remaining;
    }

    public void updateDate(Date date) {
        this.date = date;
    }

    public void complete() {
        this.status = 1;
        this.remaining -= 1;
    }

    public void cancel() {
        this.status = -1;
        this.remaining--;
    }

    public boolean isValidate() {
        return (isValidDate() && isRemain());
    }

    private boolean isValidDate() {
        Date today = new Date();
        return today.before(this.date);
    }

    private boolean isRemain() {
        return this.remaining > 0;
    }






}
