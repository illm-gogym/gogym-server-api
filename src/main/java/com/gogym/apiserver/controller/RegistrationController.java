package com.gogym.apiserver.controller;

import com.gogym.apiserver.controller.response.BasicResponse;
import com.gogym.apiserver.controller.response.CommonResponse;
import com.gogym.apiserver.dto.reservation.ReservationUpdateRequestDto;
import com.gogym.apiserver.dto.registration.RegistrationRequestDtoByUserPhone;
import com.gogym.apiserver.service.RegistrationService;
import com.gogym.apiserver.utils.SecurityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Server error")
})

@Slf4j
@RestController
@RequestMapping("api/auth/registration")
@AllArgsConstructor
@Api(tags = {"수강권 관련 API"})
public class RegistrationController {
    private static final String API_NAME = "api/auth/registration";
    private final RegistrationService registrationService;

    @GetMapping("user/get")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @ApiOperation(value = "회원 수강권 정보", notes = "회원이 본인의 수강권 정보를 가져온다.")
    public ResponseEntity<? extends BasicResponse> getRegistrationByUser() {
        System.out.println(API_NAME + "/user/get");
        return ResponseEntity.ok(new CommonResponse<>(registrationService.getRegistrationByUserPhone(SecurityUtil.getCurrentTrainerId().get())));
    }

    @GetMapping("trainer/get")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ApiOperation(value = "트레이너별 회원들 수강권 정보", notes = "트레이너가 본인 회원들의 수강권 정보를 가져온다.")
    public ResponseEntity<? extends BasicResponse> getRegistrationByTrainerId() {
        System.out.println(API_NAME + "/trainer/get");
        return ResponseEntity.ok(new CommonResponse<>(registrationService.getRegistrationByTrainerId(SecurityUtil.getCurrentTrainerId().get())));
    }

    @PostMapping("update")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ApiOperation(value = "회원 수강권 정보 수정", notes = "트레이너가 회원의 수강권 정보를 수정한다.")
    public ResponseEntity<? extends BasicResponse> updateRegistration(@Valid @RequestBody ReservationUpdateRequestDto requestDto) {
        log.info(API_NAME + "/update");
        return ResponseEntity.ok(new CommonResponse<>(registrationService.updateRegistration(requestDto)));
    }
    @PostMapping("byuser")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @ApiOperation(value = "회원 별 수강권 및 개인 정보 조회", notes = "트레이너가 특정 회원의 수강권 정보와 개인정보를 가져온다.")
    public ResponseEntity<? extends BasicResponse> getRegistrationAndDetailByUserPhone(@Valid @RequestBody RegistrationRequestDtoByUserPhone requestDto) {
        System.out.println(API_NAME + "/byuser");
        return ResponseEntity.ok(new CommonResponse<>(registrationService.getRegistrationAndDetailByUserPhone(SecurityUtil.getCurrentTrainerId().get(), requestDto)));
    }
}
