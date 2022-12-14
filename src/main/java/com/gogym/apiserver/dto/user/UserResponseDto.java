package com.gogym.apiserver.dto.user;

import com.gogym.apiserver.entity.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private User user;
    private String token;

    @Builder
    public UserResponseDto(User user, String token) {
        this.user = user;
        this.token = token;
    }
}