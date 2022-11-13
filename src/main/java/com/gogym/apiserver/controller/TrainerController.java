package com.gogym.apiserver.controller;

import com.gogym.apiserver.controller.response.BasicResponse;
import com.gogym.apiserver.controller.response.CommonResponse;
import com.gogym.apiserver.dto.trainer.TrainerSaveRequestDto;
import com.gogym.apiserver.service.TrainerService;
import com.gogym.apiserver.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/auth/trainer")
@AllArgsConstructor
@Api(tags = {"TRAINER 관련 API"})
public class TrainerController {
    private static final String API_NAME = "api/auth/trainer";
    private final TrainerService trainerService;
    private final UserService userService;

    @PostMapping("signup")
    @ApiOperation(value = "회원가입", notes = "트레이너가 회원가입 할 수 있다.")
    public ResponseEntity<? extends BasicResponse> signup(@Valid @RequestBody TrainerSaveRequestDto requestDto) {
        System.out.println(API_NAME + "/signup");
        return ResponseEntity.ok(new CommonResponse<>(trainerService.signup(requestDto)));
    }

    @GetMapping("userall")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "트레이너별 회원 검색", notes = "해당 트레이너의 모든 회원 정보를 가져올 수 있다.")
    public ResponseEntity<? extends BasicResponse> getUsers(@RequestParam("trainer_id") String trainerId) {
        System.out.println(API_NAME + "/userall");
        return ResponseEntity.ok(new CommonResponse<>(userService.getUsersByTrainerId(trainerId)));
    }
}
