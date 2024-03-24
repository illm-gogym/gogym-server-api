package com.gogym.apiserver.repository.workout.descriptions;

import com.gogym.apiserver.dto.workout.descriptions.WorkoutDescriptions;
import com.gogym.apiserver.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkoutDescriptionsRepository extends JpaRepository<WorkoutDescriptions, Long> {
    Optional<WorkoutDescriptions> findWorkoutDescriptionsByReservationId(String reservationId);
}
