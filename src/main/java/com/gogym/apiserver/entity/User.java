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
import java.util.Date;

@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends BaseTimeEntity {

        @Id
        @Column(name="user_phone")
        private String userPhone;


        @Column(name="user_password")
        @JsonIgnore
        private String password;

        @Column(name="user_name")
        private String name;

        @Column(name="user_gender")
        private String gender;

        @Column(name="user_birth")
        @Temporal(TemporalType.DATE)
        private Date date;

        @Enumerated(EnumType.STRING)
        private Role role;

    }
