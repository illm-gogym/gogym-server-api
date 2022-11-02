package com.gogym.apiserver.controller;

import com.gogym.apiserver.dto.token.TokenResponseDto;
import com.gogym.apiserver.dto.user.UserLoginDto;
import com.gogym.apiserver.jwt.JwtFilter;
import com.gogym.apiserver.service.AuthService;
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
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("authenticate")
    public ResponseEntity<TokenResponseDto> authorize(@Valid @RequestBody UserLoginDto loginDto) {
        TokenResponseDto tokenResponseDto = authService.login(loginDto);

        // 1. Response Header에 token 값을 넣어준다.
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + tokenResponseDto.getToken());

        // 2. Response Body에 token 값을 넣어준다.
        return new ResponseEntity<>(tokenResponseDto, httpHeaders, HttpStatus.OK);
    }
}