package com.gogym.apiserver.dto.gym;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.gogym.apiserver.entity.common.BaseTimeEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Gym extends BaseTimeEntity {
    @Id
    @Column(name = "gym_id")
    private String gymId;
    @Column(name = "gym_name")
    private String gymName;
    @Column(name = "gym_tel")
    private String gymTel;
    @Column(name = "gym_address")
    private String gymAddress;

    public void printGym() {
        System.out.println("gym_id : " + this.gymId);
        System.out.println("gym_name : " + this.gymName);
    }

    public void updateGym(Gym gym) {
        this.gymName = gym.getGymName();
        this.gymTel = gym.getGymTel();
        this.gymAddress = gym.getGymAddress();
    }

}
