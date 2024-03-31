package com.gogym.apiserver.service.workout;

import com.gogym.apiserver.dto.reservation.ReservationDto;
import com.gogym.apiserver.dto.reservation.ReservationSaveRequestDto;
import com.gogym.apiserver.dto.workout.descriptions.WorkoutDescriptions;
import com.gogym.apiserver.entity.Reservation;
import com.gogym.apiserver.error.common.ErrorCode;
import com.gogym.apiserver.error.common.ErrorResponse;
import com.gogym.apiserver.error.exception.CommonException;
import com.gogym.apiserver.repository.workout.descriptions.WorkoutDescriptionsRepository;
import com.gogym.apiserver.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.jdbc.Work;
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

    public WorkoutDescriptions updateWorkoutDescriptions(String reservationId, String description) {
        List<WorkoutDescriptions> wd = workoutDescriptionsRepository.findWorkoutDescriptionsByReservationId(reservationId);
        if (wd.size() == 0) {
            throw new CommonException(ErrorCode.NOT_FOUND_RESERVATION);
        }
        WorkoutDescriptions workoutDescriptions = wd.get(0);
        workoutDescriptions.updateWorkoutDescriptions(description);
        return workoutDescriptionsRepository.save(workoutDescriptions);
    }

    public WorkoutDescriptions deleteWorkoutDescriptions(String reservationId) {
        List<WorkoutDescriptions> wd = workoutDescriptionsRepository.findWorkoutDescriptionsByReservationId(reservationId);
        if (wd.size() == 0) {
            throw new CommonException(ErrorCode.NOT_FOUND_RESERVATION);
        }

        WorkoutDescriptions workoutDescriptions = wd.get(0);
        workoutDescriptionsRepository.delete(workoutDescriptions);

        return workoutDescriptions;
    }
}
