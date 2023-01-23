package com.gogym.apiserver.controller;

import com.gogym.apiserver.dto.token.TokenResponseDto;
import com.gogym.apiserver.dto.trainer.TrainerLoginDto;
import com.gogym.apiserver.dto.user.UserLoginDto;
import com.gogym.apiserver.dto.user.UserResponseDto;
import com.gogym.apiserver.jwt.JwtFilter;
import com.gogym.apiserver.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/authenticate/login")
@RequiredArgsConstructor
public class AuthController {
    private static final String API_NAME = "api/authenticate/login/";
    private final AuthService authService;

    @PostMapping("trainer")
    public ResponseEntity<TokenResponseDto> loginForTrainer(@Valid @RequestBody TrainerLoginDto loginDto) {
        System.out.println(API_NAME + "trainer");
        TokenResponseDto tokenResponseDto = authService.login(loginDto);

        // 1. Response Header에 token 값을 넣어준다.
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + tokenResponseDto.getToken());

        // 2. Response Body에 token 값을 넣어준다.
        return new ResponseEntity<>(tokenResponseDto, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("user")
    public ResponseEntity<UserResponseDto> loginForUser(@Valid @RequestBody UserLoginDto loginDto) {
        System.out.println(API_NAME + "user");
        UserResponseDto userResponseDto = authService.loginForUser(loginDto);

        // 1. Response Header에 token 값을 넣어준다.
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + userResponseDto.getToken());

        // 2. Response Body에 token 값을 넣어준다.
        return new ResponseEntity<>(userResponseDto, httpHeaders, HttpStatus.OK);
    }
}