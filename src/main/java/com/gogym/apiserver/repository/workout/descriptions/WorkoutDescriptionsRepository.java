package com.gogym.apiserver.repository.workout.descriptions;

import com.gogym.apiserver.dto.workout.descriptions.WorkoutDescriptions;
import com.gogym.apiserver.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface WorkoutDescriptionsRepository extends JpaRepository<WorkoutDescriptions, Long> {
    @Query("SELECT wd FROM WorkoutDescriptions wd WHERE wd.reservationId = :reservationId")
    List<WorkoutDescriptions> findWorkoutDescriptionsByReservationId(String reservationId);
}
