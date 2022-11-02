package com.gogym.apiserver.controller;

import com.gogym.apiserver.controller.response.BasicResponse;
import com.gogym.apiserver.controller.response.CommonResponse;
import com.gogym.apiserver.dto.user.UserSaveRequestDto;
import com.gogym.apiserver.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/auth/user")
@AllArgsConstructor
public class UserController {
    private static final String API_NAME = "api/auth/user";
    private final UserService userService;

    @GetMapping("all")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<? extends BasicResponse> getUsers() {
        System.out.println(API_NAME + "/all");
        return ResponseEntity.ok(new CommonResponse<>(userService.getUsers()));
    }

    @PostMapping("signup")
    public ResponseEntity<? extends BasicResponse> signup(@Valid @RequestBody UserSaveRequestDto requestDto) {
        System.out.println(API_NAME + "/signup");
        return ResponseEntity.ok(new CommonResponse<>(userService.signup(requestDto)));
    }
}
