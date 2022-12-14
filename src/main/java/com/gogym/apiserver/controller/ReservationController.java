package com.gogym.apiserver.controller;

import com.gogym.apiserver.controller.response.BasicResponse;
import com.gogym.apiserver.controller.response.CommonResponse;
import com.gogym.apiserver.dto.reservation.ReservationSaveRequestDto;
import com.gogym.apiserver.dto.reservation.ReservationUpdateRequestDto;
import com.gogym.apiserver.dto.reservation.ReservationViewRequestDto;
import com.gogym.apiserver.service.ReservationService;
import com.gogym.apiserver.utils.SecurityUtil;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("api/auth/reservation")
@AllArgsConstructor
public class ReservationController {
    private static final String API_NAME = "api/auth/reservation";
    private final ReservationService reservationService;

    @PostMapping("add")
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    @ApiOperation(value = "일정 등록", notes = "트레이너가 회원 일정을 등록할 수 있다.")
    public ResponseEntity<? extends BasicResponse> addSchedule(@Valid @RequestBody ReservationSaveRequestDto requestDto) {
        log.info(API_NAME + "/add");
        return ResponseEntity.ok(new CommonResponse<>(reservationService.addSchedule(requestDto)));
    }

    @PostMapping("update")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "일정 수정", notes = "트레이너가 회원 일정을 수정할 수 있다.")
    public ResponseEntity<? extends BasicResponse> updateSchedule(@Valid @RequestBody ReservationUpdateRequestDto requestDto) {
        log.info(API_NAME + "/update");
        return ResponseEntity.ok(new CommonResponse<>(reservationService.updateSchedule(requestDto)));
    }

    @PostMapping("delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "일정 삭제", notes = "트레이너가 회원 일정을 삭제할 수 있다.")
    public ResponseEntity<? extends BasicResponse> deleteSchedule(@PathVariable("registration_id") Long id) {
        log.info(API_NAME + "/delete");
        return ResponseEntity.ok(new CommonResponse<>(reservationService.deleteSchedule(id)));
    }

    @GetMapping("all")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "일정 검색", notes = "트레이너가 회원 일정을 모두 조회할 수 있다.")
    public ResponseEntity<? extends BasicResponse> getScheduleByTrainer() {
        System.out.println(API_NAME + "/add");
        return ResponseEntity.ok(new CommonResponse<>(reservationService.getScheduleByTrainer(SecurityUtil.getCurrentTrainerId().get())));
    }

    @PostMapping("all/user")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "회원별 일정 검색", notes = "트레이너가 회원별 일정을 모두 조회할 수 있다.")
    public ResponseEntity<? extends BasicResponse> getScheduleByUserPhone(@Valid @RequestBody ReservationViewRequestDto requestDto) {
        log.info(API_NAME + "/add/user");
        return ResponseEntity.ok(new CommonResponse<>(reservationService.getScheduleByUserPhone(requestDto)));
    }

    @PostMapping("all/time")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "시간별 일정 검색", notes = "트레이너가 원하는 시간대의 일정을 모두 조회할 수 있다.")
    public ResponseEntity<? extends BasicResponse> getScheduleByTime(@Valid @RequestBody ReservationViewRequestDto requestDto) {
        log.info(API_NAME + "/add/time");
        return ResponseEntity.ok(new CommonResponse<>(reservationService.getScheduleByTime(requestDto)));
    }
}
