package com.gogym.apiserver.service.workout;

import com.gogym.apiserver.dto.reservation.ReservationDto;
import com.gogym.apiserver.dto.reservation.ReservationSaveRequestDto;
import com.gogym.apiserver.dto.workout.descriptions.WorkoutDescriptions;
import com.gogym.apiserver.entity.Reservation;
import com.gogym.apiserver.error.common.ErrorCode;
import com.gogym.apiserver.error.common.ErrorResponse;
import com.gogym.apiserver.repository.workout.descriptions.WorkoutDescriptionsRepository;
import com.gogym.apiserver.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkoutDescriptionsService {
    private final WorkoutDescriptionsRepository workoutDescriptionsRepository;
    public WorkoutDescriptions makeWorkoutDescriptions(ReservationDto reservationDto, String reservationId) {
        return WorkoutDescriptions.builder()
                .reservationId(reservationId)
                .description(reservationDto.getDescription())
                .build();
    }

    public List<WorkoutDescriptions> saveWorkoutDescriptions(List<WorkoutDescriptions> workoutDescriptions) {
        return workoutDescriptionsRepository.saveAll(workoutDescriptions);
    }

    public WorkoutDescriptions updateWokroutDescriptions(String reservationId) {
        Optional<WorkoutDescriptions> wd = workoutDescriptionsRepository.findWorkoutDescriptionsByReservationId(reservationId);
        if (!wd.isPresent()) {
            new ErrorResponse(ErrorCode.NOT_FOUND_RESERVATION);
        }
        WorkoutDescriptions workoutDescriptions = wd.get();
        return workoutDescriptionsRepository.save(WorkoutDescriptions.builder()
                .reservationId(reservationId)
                .description(workoutDescriptions.getDescription())
                .build());
    }
}
