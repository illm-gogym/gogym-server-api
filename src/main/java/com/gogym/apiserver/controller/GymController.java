package com.gogym.apiserver.controller;

import com.gogym.apiserver.controller.response.CommonResponse;
import com.gogym.apiserver.dto.gym.Gym;
import com.gogym.apiserver.service.GymService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ApiResponses({
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 500, message = "Server error")
})

@RestController
@RequestMapping("/api/auth/gym")
@AllArgsConstructor
@Api(tags = {"Gym 관련 API"})
public class GymController {
    private static final String API_NAME = "/api/auth/gym";
    private final GymService gymService;

    @PostMapping("add")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "Gym 생성", notes = "Gym 생성 ")
    public ResponseEntity<CommonResponse> addGym(@RequestBody Gym req) {
        System.out.println(API_NAME + "/");
        return new ResponseEntity<>(new CommonResponse<>(gymService.addGym(req)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "Gym 조회", notes = "Gym 조회")
    public ResponseEntity<CommonResponse> getGymById(@PathVariable("id") String id) {
        return new ResponseEntity<>(new CommonResponse<>(gymService.getGymById(id)), HttpStatus.OK);
    }

    @GetMapping("/")
    @ApiOperation(value = "Gym 전체 조회", notes = "Gym 전체 조회")
    public ResponseEntity<CommonResponse> getGymByAll() {
        return new ResponseEntity<>(new CommonResponse<>(gymService.getGymByAll()), HttpStatus.OK);
    }

    @PutMapping("/")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "Gym 수정", notes = "Gym 수정")
    public ResponseEntity<CommonResponse> updateGym(@RequestBody Gym req) {
        return new ResponseEntity<>(new CommonResponse<>(gymService.updateGym(req)), HttpStatus.OK);
    }

    @DeleteMapping("/")
    //@PreAuthorize("hasAnyRole('ADMIN')")
    @ApiOperation(value = "Gym 삭제", notes = "Gym 삭제")
    public ResponseEntity<CommonResponse> deleteGym(@PathVariable("id") String id) {
        return new ResponseEntity<>(new CommonResponse<>(gymService.deleteGym(id)), HttpStatus.OK);
    }
}
