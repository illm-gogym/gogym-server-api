package com.gogym.apiserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.gogym.apiserver.entity.common.BaseTimeEntity;
import com.gogym.apiserver.entity.common.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Trainer extends BaseTimeEntity {
    @Id
    @Column(name = "trainer_id")
    private String trainerId;

    @Column(name = "trainer_password")
    @JsonIgnore
    private String password;

    @Column(name = "trainer_name")
    private String name;

    @Column(name = "trainer_phone")
    private String trainerPhone;

    @Column(name = "gym_id")
    private Long gymId;


    @Enumerated(EnumType.STRING)
    private Role role;

}
