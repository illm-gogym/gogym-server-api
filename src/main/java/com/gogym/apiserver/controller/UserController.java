package com.gogym.apiserver.controller;

import com.gogym.apiserver.controller.response.BasicResponse;
import com.gogym.apiserver.controller.response.CommonResponse;
import com.gogym.apiserver.dto.user.UserSaveRequestDto;
import com.gogym.apiserver.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Server error")
})

@RestController
@RequestMapping("api/auth/user")
@AllArgsConstructor
@Api(tags = {"User 관련 API"})
public class UserController {
    private static final String API_NAME = "api/auth/user";
    private final UserService userService;

    @GetMapping("all")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "모든 회원 검색", notes = "모든 회원 정보를 가져올 수 있다.")
    public ResponseEntity<? extends BasicResponse> getUsers() {
        System.out.println(API_NAME + "/all");
        return ResponseEntity.ok(new CommonResponse<>(userService.getUsers()));
    }

    @PostMapping("signup")
    @ApiOperation(value = "회원가입", notes = "회원이 회원가입 할 수 있다.")
    public ResponseEntity<? extends BasicResponse> signup(@Valid @RequestBody UserSaveRequestDto requestDto) {
        System.out.println(API_NAME + "/signup");
        return ResponseEntity.ok(new CommonResponse<>(userService.signup(requestDto)));
    }
}
